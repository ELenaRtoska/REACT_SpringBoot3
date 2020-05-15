package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.service.IngredientsService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientsApi {

    private final IngredientsService ingredientsService;
    private final PizzaService pizzaService;

    public IngredientsApi(IngredientsService ingredientsService, PizzaService pizzaService) {
        this.ingredientsService = ingredientsService;
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam String name,
                                       @RequestParam boolean spicy,
                                       @RequestParam float amount,
                                       @RequestParam boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder){
        Ingredient result = this.ingredientsService.create(name, spicy, amount, veggie);
        response.setHeader("Location", builder.path("/api/ingredients/{ingredientId}").buildAndExpand(result.getId()).toUriString());
        return result;
    }

    @PatchMapping(path = "/{id}")
    public Ingredient updateIngredient(@PathVariable UUID id,
                                       @RequestParam Optional<String> name,
                                       @RequestParam Optional<Boolean> spicy,
                                       @RequestParam Optional<Float> amount,
                                       @RequestParam Optional<Boolean> veggie){
        return this.ingredientsService.patchUpdate(id, name, spicy, amount, veggie);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteIngredient(@PathVariable UUID id){
        this.ingredientsService.delete(id);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable UUID id){
        return this.ingredientsService.getById(id);
    }

    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "5") int size,
                                              @RequestParam(required = false, defaultValue = "false") boolean spicy){

        if(spicy)
            return this.ingredientsService.getSpicy();
        return this.ingredientsService.getAll(page, size);
    }

    @GetMapping("/all")
    public List<Ingredient> getAll(){
        return this.ingredientsService.getAll();
    }

    @GetMapping(path = "/veggie")
    public List<Ingredient> getVeggieIngredients(){
        return this.ingredientsService.getVeggie();
    }

    @GetMapping("/{ingredientId}/pizzas")
    public List<Pizza> getPizzasWithIngredient(@PathVariable UUID ingredientId){
        return this.pizzaService.getAllContainingIngredient(ingredientId);
    }


}
