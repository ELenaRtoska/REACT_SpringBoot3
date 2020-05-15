package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
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
@RequestMapping(path = "/api/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class PizzasApi {

    private final PizzaService pizzaService;
    private final IngredientsService ingredientsService;

    public PizzasApi(PizzaService pizzaService, IngredientsService ingredientsService) {
        this.pizzaService = pizzaService;
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pizza createPizza(@RequestParam String type,
                             @RequestParam String description,
                             @RequestParam Optional<Boolean> veggie,
                             @RequestParam List<UUID> ingredients,
                             HttpServletResponse response,
                             UriComponentsBuilder builder){
        Pizza result = this.pizzaService.create(type, description, veggie.orElse(false), ingredients);
        response.setHeader("Location", builder.path("/api/pizzas/{pizzaId}").buildAndExpand(result.getId()).toUriString());
        return result;
    }

    @PutMapping(path = "/{id}")
    public Pizza updatePizza(@PathVariable UUID id,
                             @RequestParam String type,
                             @RequestParam String description,
                             @RequestParam Optional<Boolean> veggie,
                             @RequestParam List<UUID> ingredients){
        return this.pizzaService.putUpdate(id, type, description, veggie.orElse(false), ingredients);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePizza(@PathVariable UUID id){
        this.pizzaService.delete(id);
    }

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam Optional<Integer> totalIngredients){
        if(totalIngredients.isPresent())
            return this.pizzaService.getAllWithIngredientsCountLessThan(totalIngredients.get());
        return this.pizzaService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Pizza getPizza(@PathVariable  UUID id){
        return this.pizzaService.getById(id);
    }

    @GetMapping(path = "/compare")
    public List<Ingredient> comparePizzas(@RequestParam UUID pizza1, @RequestParam UUID pizza2){
        return this.ingredientsService.getCommonIngredientsBetween(pizza1, pizza2);
    }

    @GetMapping(path = "/spicy")
    public List<Pizza> getPizzasWithSpicyIngredient(){
        return this.pizzaService.getAllWithSpicyIngredient();
    }

}
