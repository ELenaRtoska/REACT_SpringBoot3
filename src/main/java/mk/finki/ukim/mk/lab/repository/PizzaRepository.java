package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {

    public List<Pizza> findAllByIngredientsContaining(Ingredient ingredient);

    public List<Pizza> findDistinctByIngredients_SpicyIsTrue();

    @Query("select p from pizzas p where p.ingredients.size <= :totalIngredients")
    public List<Pizza> findAllWithIngredientsLessThan(int totalIngredients);
}
