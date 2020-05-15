package mk.finki.ukim.mk.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class DataHolder {

    private static List<Pizza> initialPizzas = new ArrayList<>();

    private static List<Ingredient> initialIngredients = new ArrayList<>();

    private final IngredientRepository ingredientRepository;

    private final PizzaRepository pizzaRepository;

    public DataHolder(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @PostConstruct
    public void init(){

        initialIngredients.add(new Ingredient("domat", false, 2, true));
        initialIngredients.add(new Ingredient("cili", true, 5, true));
        initialIngredients.add(new Ingredient("salama", false, 1, false));
        initialIngredients.add(new Ingredient("kaskaval", false, 2, true));
        initialIngredients.add(new Ingredient("bukovec", true, 2, true));
        initialIngredients.add(new Ingredient("cajna", false, 2, true));
        initialIngredients.add(new Ingredient("kulen", true, 2, false));
        initialIngredients.add(new Ingredient("pecurki", false, 2, true));
        initialIngredients.add(new Ingredient("kecap", false, 2, true));
        initialIngredients.add(new Ingredient("majonez", false, 2, false));

        this.ingredientRepository.saveAll(initialIngredients);

        initialPizzas.add(new Pizza("pizza1", "desc1", initialIngredients.subList(0,3), false));
        initialPizzas.add(new Pizza("pizza2", "desc2", initialIngredients.subList(3,4), false));
        initialPizzas.add(new Pizza("pizza3", "desc3", initialIngredients.subList(0,5), false));
        initialPizzas.add(new Pizza("pizza4", "desc4", initialIngredients.subList(2,5), false));
        initialPizzas.add(new Pizza("pizza5", "veggie1",
                initialIngredients.stream()
                        .filter(Ingredient::isVeggie)
                        .collect(Collectors.toList()),
                true));

        initialPizzas.add(new Pizza("pizza6", "veggie2",
                initialIngredients.stream()
                        .filter(Ingredient::isVeggie)
                        .collect(Collectors.toList())
                        .subList(0, 4),
                true));

        this.pizzaRepository.saveAll(initialPizzas);
    }

}
