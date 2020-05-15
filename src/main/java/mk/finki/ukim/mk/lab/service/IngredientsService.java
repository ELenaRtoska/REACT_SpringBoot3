package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientsService {

    Ingredient create(String name, boolean spicy, float amount, boolean veggie);

    Ingredient patchUpdate(UUID id, Optional<String> name, Optional<Boolean> spicy, Optional<Float> amount, Optional<Boolean> veggie);

    void delete(UUID id);

    mk.finki.ukim.mk.lab.model.vm.Page<Ingredient> getAll(int page, int size);

    Ingredient getById(UUID id);

    Page<Ingredient> getSpicy();

    List<Ingredient> getVeggie();

    List<Ingredient> getCommonIngredientsBetween(UUID pizza1Id, UUID pizza2Id);

    List<Ingredient> getAll();

}
