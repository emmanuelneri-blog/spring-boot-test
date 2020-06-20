package br.com.emmanuelneri.springboottest.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.tuple;

class RecipeTest {

    @Test
    public void shouldAddIngredientInTheRecipe() {
        final Ingredient chocolate = createIngredient(1L, "chocolate");
        final Ingredient flour = createIngredient(2L, "flour");
        final Ingredient sugar = createIngredient(3L, "sugar");
        final Ingredient milk = createIngredient(4L, "milk");
        final Ingredient egg = createIngredient(5L, "egg");

        final Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Chocolate Cake");
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

    private Ingredient createIngredient(final Long id, final String name) {
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(name);
        return ingredient;
    }

}