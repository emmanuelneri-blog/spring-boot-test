package br.com.emmanuelneri.springboottest.model;

import lombok.Getter;

@Getter
public class IngredientQuantity {

    private final Ingredient ingredient;
    private final String quantity;

    public IngredientQuantity(final Ingredient ingredient, final String quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}
