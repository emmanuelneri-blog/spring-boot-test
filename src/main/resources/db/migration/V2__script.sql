CREATE TABLE recipe
(
    id   bigserial PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE recipe_ingredient
(
    recipe_id bigint NOT NULL,
    ingredient_id bigint NOT NULL,
    quantity VARCHAR(100) NOT NULL,
    CONSTRAINT recipe_ingredient_pk PRIMARY KEY (recipe_id, ingredient_id) ,
    CONSTRAINT recipe_ingredient_recipe_id_fk FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    CONSTRAINT recipe_ingredient_ingredient_id_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);