package br.com.emmanuelneri.springboottest.repository;

import br.com.emmanuelneri.springboottest.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
