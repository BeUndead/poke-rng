package com.com.poke.model;

/**
 * Enumeration of all the various {@link Type Types} which a {@code Pokémon} can have.
 */
public enum Types implements Type {

    Normal,
    // Note : Cannot be a hidden power type.
    Fighting,
    Flying,
    Poison,
    Ground,
    Rock,
    Bug,
    Ghost,
    Steel,
    Fire,
    Water,
    Grass,
    Electric,
    Psychic,
    Ice,
    Dragon,
    Dark,
    Fairy; // Note : Cannot be a hidden power type.

    /**
     * {@inheritDoc}
     */
    @Override
    public int index() {

        return this.ordinal();
    }
}
