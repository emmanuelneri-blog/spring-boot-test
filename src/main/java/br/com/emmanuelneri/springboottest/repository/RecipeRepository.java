package br.com.emmanuelneri.springboottest.repository;

import br.com.emmanuelneri.springboottest.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}