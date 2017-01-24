package com.com.poke.model;

/**
 * Represents the various {@code Stats} of a {@code Pokémon}.
 */
public interface Stat {

    /**
     * The internal, in-game {@code index} of this {@link Stat}.
     *
     * @return The in-game {@code index} of this {@code Stat}.
     */
    int index();
}
