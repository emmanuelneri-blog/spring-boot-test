package br.com.emmanuelneri.springboottest.service;

import br.com.emmanuelneri.springboottest.config.PostgresContainerInitializer;
import br.com.emmanuelneri.springboottest.model.Ingredient;
import br.com.emmanuelneri.springboottest.model.IngredientQuantity;
import br.com.emmanuelneri.springboottest.model.Recipe;
import br.com.emmanuelneri.springboottest.model.RecipeIngredient;
import br.com.emmanuelneri.springboottest.repository.IngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.tuple;


@SpringBootTest
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
class RecipeServiceIT {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    @Sql(scripts = "classpath:scripts/insert-default-ingredient.sql")
    public void shouldCreateRecipe() {
        final Map<String, Ingredient> ingredientByName = ingredientRepository.findAll().stream()
                .collect(Collectors.toMap(Ingredient::getName, Function.identity()));

        final List<IngredientQuantity> ingredientQuantities = new ArrayList<>();
        ingredientQuantities.add(new IngredientQuantity(ingredientByName.get("chocolate"), "one cup"));
        ingredientQuantities.add(new IngredientQuantity(ingredientByName.get("flour"), "two cup"));
        ingredientQuantities.add(new IngredientQuantity(ingredientByName.get("sugar"), "one cup"));
        ingredientQuantities.add(new IngredientQuantity(ingredientByName.get("milk"), "half a cup"));
        ingredientQuantities.add(new IngredientQuantity(ingredientByName.get("egg"), "three units"));

        final Recipe recipe = recipeService.create("Chocolate Cake", ingredientQuantities);
        Assert.assertNotNull(recipe.getId());
        Assertions.assertThat(recipe.getIngredients())
                .extracting(r -> r.getId().getRecipe().getId(), RecipeIngredient::getQuantity)
                .containsExactlyInAnyOrder(tuple(recipe.getId(), "one cup"),
                        tuple(recipe.getId(), "two cup"),
                        tuple(recipe.getId(), "one cup"),
                        tuple(recipe.getId(), "half a cup"),
                        tuple(recipe.getId(), "three units"));
    }
}