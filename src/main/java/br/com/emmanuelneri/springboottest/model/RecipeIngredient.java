package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "recipe_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @NotNull
    private String quantity;

    public RecipeIngredient(final Recipe recipe, final Ingredient ingredient, final String quantity) {
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
        this.quantity = quantity;
    }

    @Embeddable
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    public static class RecipeIngredientId implements Serializable {

        @Column(name = "recipe_id")
        private Long recipeId;

        @Column(name = "ingredient_id")
        private Long ingredientId;
    }
}
