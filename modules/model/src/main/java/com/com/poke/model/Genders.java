package com.com.poke.model;

/**
 * Represents the various {@link Genders Genders} that a {@code Pokémon} may have.
 */
public enum Genders implements Gender {

    /**
     * The {@code Pokémon} is {@code Male}.
     */
    Male,

    /**
     * The {@code Pokémon} is {@code Female}.
     */
    Female,

    /**
     * The {@code Pokémon} has no {@link Gender}.
     */
    Genderless
}
