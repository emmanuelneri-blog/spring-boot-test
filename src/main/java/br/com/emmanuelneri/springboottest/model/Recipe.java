package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public Recipe(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public void add(final Ingredient ingredient, final String quantity) {
        final RecipeIngredient recipeIngredient = new RecipeIngredient(this, ingredient, quantity);
        ingredients.add(recipeIngredient);
    }

}
