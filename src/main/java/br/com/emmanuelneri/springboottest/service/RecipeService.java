package br.com.emmanuelneri.springboottest.service;

import br.com.emmanuelneri.springboottest.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

}