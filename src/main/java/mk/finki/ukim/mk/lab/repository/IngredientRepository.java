package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    public Page<Ingredient> findAllBySpicyIsTrue(Pageable pageable);

    public List<Ingredient> findAllByVeggieIsTrue();

    public List<Ingredient> findAllByPizzasContainsAndPizzasContains(Pizza p1, Pizza p2);

    public int countAllBySpicyIsTrue();

    public boolean existsByName(String name);

}
