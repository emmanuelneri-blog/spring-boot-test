package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "recipe_ingredient")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    private String quantity;

    public RecipeIngredient(final Recipe recipe, final Ingredient ingredient, final String quantity) {
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
        this.quantity = quantity;
    }

    @Embeddable
    @Data
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RecipeIngredientId implements Serializable {
        private Long recipeId;
        private Long ingredientId;
    }
}
