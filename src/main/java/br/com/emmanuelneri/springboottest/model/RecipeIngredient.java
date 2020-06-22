package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "recipe_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @NotNull
    private String quantity;

    public RecipeIngredient(final Recipe recipe, final Ingredient ingredient, final String quantity) {
        this.id = new RecipeIngredientId(recipe, ingredient);
        this.quantity = quantity;
    }

    @Embeddable
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @EqualsAndHashCode
    public static class RecipeIngredientId implements Serializable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "recipe_id")
        private Recipe recipe;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ingredient_id")
        private Ingredient ingredient;
    }
}
