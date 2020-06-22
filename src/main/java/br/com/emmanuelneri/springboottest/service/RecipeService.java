package br.com.emmanuelneri.springboottest.service;

import br.com.emmanuelneri.springboottest.model.IngredientQuantity;
import br.com.emmanuelneri.springboottest.model.Recipe;
import br.com.emmanuelneri.springboottest.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Recipe create(final String name, final List<IngredientQuantity> ingredientQuantities) {
        final Recipe recipe = new Recipe(name);
        ingredientQuantities.forEach(ingredient -> recipe.add(ingredient.getIngredient(), ingredient.getQuantity()));
        return recipeRepository.save(recipe);
    }

}