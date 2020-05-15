package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.*;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientsService {

    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Ingredient create(String name, boolean spicy, float amount, boolean veggie) {
        this.createIngredientConstraintsCheck(name, spicy);
        return this.ingredientRepository.save(new Ingredient(name, spicy, amount, veggie));
    }

    private void createIngredientConstraintsCheck(String name, boolean spicy){
        if(this.ingredientRepository.existsByName(name))
            throw new IngredientNameException(name);
        if(spicy && this.ingredientRepository.countAllBySpicyIsTrue() == 3)
            throw new SpicyIngredientException();
    }

    private void patchIngredientNameConstraintCheck(String oldName, String newName){
        if(!oldName.equals(newName) && this.ingredientRepository.existsByName(newName))
            throw new IngredientNameException(newName);
    }

    private void patchIngredientSpicyConstraint(boolean wasSpicy, boolean isSpicy){
        if(!wasSpicy && isSpicy && this.ingredientRepository.countAllBySpicyIsTrue() == 3)
            throw new SpicyIngredientException();
    }


    @Override
    public Ingredient patchUpdate(UUID id, Optional<String> name, Optional<Boolean> spicy, Optional<Float> amount, Optional<Boolean> veggie) {
        Ingredient toEdit = this.ingredientRepository.findById(id).orElseThrow(InvalidIngredientIdException::new);

        name.ifPresent((newName) -> this.patchIngredientNameConstraintCheck(toEdit.getName(), newName));
        spicy.ifPresent((isSpicy) -> this.patchIngredientSpicyConstraint(toEdit.isSpicy(), isSpicy));

        name.ifPresent(toEdit::setName);
        spicy.ifPresent(toEdit::setSpicy);
        amount.ifPresent(toEdit::setAmount);
        veggie.ifPresent(toEdit::setVeggie);

        return this.ingredientRepository.save(toEdit);
    }

    @Override
    public void delete(UUID id) {
        this.ingredientRepository.deleteById(id);
    }

    @Override
    public mk.finki.ukim.mk.lab.model.vm.Page<Ingredient> getAll(int page, int size) {
        if(size > 10)
            throw new InvalidPageException();
        Page<Ingredient> repoPage = this.ingredientRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        return new mk.finki.ukim.mk.lab.model.vm.Page<>(page, repoPage.getTotalPages(), size, repoPage.getContent());
    }

    @Override
    public Ingredient getById(UUID id) {
        return this.ingredientRepository.findById(id).orElseThrow(InvalidIngredientIdException::new);
    }

    @Override
    public mk.finki.ukim.mk.lab.model.vm.Page<Ingredient> getSpicy() {
        int size = this.ingredientRepository.countAllBySpicyIsTrue();
        Page<Ingredient> repoPage = this.ingredientRepository.findAllBySpicyIsTrue(PageRequest.of(0, size, Sort.by("name")));
        return new mk.finki.ukim.mk.lab.model.vm.Page<>(0, repoPage.getTotalPages(), size, repoPage.getContent());
    }

    @Override
    public List<Ingredient> getVeggie() {
        return this.ingredientRepository.findAllByVeggieIsTrue();
    }

    @Override
    public List<Ingredient> getCommonIngredientsBetween(UUID pizza1Id, UUID pizza2Id) {
        Pizza p1 = this.pizzaRepository.findById(pizza1Id).orElseThrow(InvalidPizzaIdException::new);
        Pizza p2 = this.pizzaRepository.findById(pizza2Id).orElseThrow(InvalidPizzaIdException::new);
        return this.ingredientRepository.findAllByPizzasContainsAndPizzasContains(p1, p2);
    }

    @Override
    public List<Ingredient> getAll() {
        return this.ingredientRepository.findAll();
    }
}
