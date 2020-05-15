package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngredientIdException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidPizzaIdException;
import mk.finki.ukim.mk.lab.model.exceptions.VeggiePizzaException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, IngredientRepository ingredientRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Pizza> getAll() {
        return this.pizzaRepository.findAll();
    }

    @Override
    public Pizza create(String pizzaType, String description){
        return this.pizzaRepository.save(new Pizza(pizzaType, description));
    }

    @Override
    public Pizza create(String pizzaType, String description, boolean veggie, List<UUID> ingredientsIds) {
        List<Ingredient> ingredients = this.ingredientRepository.findAllById(ingredientsIds);
        if(veggie)
            this.allVeggieIngredientsCheck(ingredients);
        return this.pizzaRepository.save(new Pizza(pizzaType, description, ingredients, veggie));
    }

    private void allVeggieIngredientsCheck(List<Ingredient> ingredients){
        if(!ingredients.stream().allMatch(Ingredient::isVeggie))
            throw new VeggiePizzaException();
    }

    @Override
    public List<Pizza> getAllContainingIngredient(UUID ingredientId) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientIdException::new);
        return this.pizzaRepository.findAllByIngredientsContaining(ingredient);
    }

    @Override
    public Pizza putUpdate(UUID id, String pizzaType, String description, boolean veggie, List<UUID> ingredientsIds) {
        Pizza toEdit = this.pizzaRepository.findById(id).orElseThrow(InvalidPizzaIdException::new);
        List<Ingredient> ingredients = this.ingredientRepository.findAllById(ingredientsIds);
        if(veggie)
            this.allVeggieIngredientsCheck(ingredients);
        toEdit.setType(pizzaType);
        toEdit.setDescription(description);
        toEdit.setVeggie(veggie);
        toEdit.setIngredients(ingredients);
        return this.pizzaRepository.save(toEdit);
    }

    @Override
    public void delete(UUID id) {
        this.pizzaRepository.deleteById(id);
    }

    @Override
    public Pizza getById(UUID id) {
        return this.pizzaRepository.findById(id).orElseThrow(InvalidPizzaIdException::new);
    }

    @Override
    public List<Pizza> getAllWithIngredientsCountLessThan(int totalIngredients) {
        return this.pizzaRepository.findAllWithIngredientsLessThan(totalIngredients);
    }

    @Override
    public List<Pizza> getAllWithSpicyIngredient() {
        return this.pizzaRepository.findDistinctByIngredients_SpicyIsTrue();
    }

}
