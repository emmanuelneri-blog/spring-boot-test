package br.com.emmanuelneri.springboottest.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_seq")
    @SequenceGenerator(name = "ingredient_id_seq", sequenceName = "ingredient_id_seq", allocationSize = 1)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @NotNull
    @Size(min = 3, max = 200)
    @Setter
    private String name;
}
