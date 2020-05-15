package mk.finki.ukim.mk.lab.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pizza_id")
    private UUID id;

    @Column(name = "pizza_type")
    private String type;

    @Column(name = "pizza_description")
    private String description;

    @Column(name = "pizza_veggie")
    private boolean veggie;

    @ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    public Pizza(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Pizza(String type, String description, List<Ingredient> ingredients, boolean veggie) {
        this.type = type;
        this.description = description;
        this.ingredients = ingredients;
        this.veggie = veggie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id.equals(pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
