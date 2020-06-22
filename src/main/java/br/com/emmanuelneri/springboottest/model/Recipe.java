package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_seq")
    @SequenceGenerator(name = "recipe_id_seq", sequenceName = "recipe_id_seq", allocationSize = 1)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @NotNull
    @Size(min = 3, max = 200)
    @Setter
    private String name;

    public Recipe(final String name) {
        this.name = name;
    }

    @NotNull
    @OneToMany(mappedBy = "id.recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public void add(final Ingredient ingredient, final String quantity) {
        final RecipeIngredient recipeIngredient = new RecipeIngredient(this, ingredient, quantity);
        ingredients.add(recipeIngredient);
    }

}
