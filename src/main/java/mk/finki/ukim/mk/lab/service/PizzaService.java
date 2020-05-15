package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;
import java.util.UUID;

public interface PizzaService {

    List<Pizza> getAll();

    Pizza create(String pizzaType, String description);

    Pizza create(String pizzaType, String description, boolean veggie, List<UUID> ingredientsIds);

    List<Pizza> getAllContainingIngredient(UUID ingredientId);

    Pizza putUpdate(UUID id, String pizzaType, String description, boolean veggie, List<UUID> ingredientsIds);

    void delete(UUID id);

    Pizza getById(UUID id);

    List<Pizza> getAllWithIngredientsCountLessThan(int totalIngredients);

    List<Pizza> getAllWithSpicyIngredient();

}
