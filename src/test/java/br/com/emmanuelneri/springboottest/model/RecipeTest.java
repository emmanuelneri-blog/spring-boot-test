package br.com.emmanuelneri.springboottest.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.tuple;

class RecipeTest {

    @Test
    public void shouldAddIngredientInTheRecipe() {
        final Ingredient chocolate = new Ingredient(1L, "chocolate");
        final Ingredient flour = new Ingredient(2L,"flour");
        final Ingredient sugar = new Ingredient(3L,"sugar");
        final Ingredient milk = new Ingredient(4L,"milk");
        final Ingredient egg = new Ingredient(5L,"egg");

        final Recipe recipe = new Recipe(1L, "Chocolate Cake");
        recipe.add(chocolate, "one cup");
        recipe.add(flour, "two cup");
        recipe.add(sugar, "one cup");
        recipe.add(milk, "half a cup");
        recipe.add(egg, "three units");

        Assertions.assertThat(recipe.getIngredients())
                .extracting(r -> r.getId().getRecipeId(), i -> i.getId().getIngredientId(), RecipeIngredient::getQuantity)
                .containsExactlyInAnyOrder(tuple(1L, 1L, "one cup"),
                        tuple(1L, 2L, "two cup"),
                        tuple(1L, 3L, "one cup"),
                        tuple(1L, 4L, "half a cup"),
                        tuple(1L, 5L, "three units"));
    }

}