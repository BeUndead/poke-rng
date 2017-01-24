package com.com.poke.model;

/**
 * Represents the various {@code Types} which a {@code Pokémon} may have.
 */
public interface Type {

    /**
     * The internal, in-game {@code index} of this {@link Type}.
     *
     * @return The in-game {@code index} of this {@code Type}.
     */
    int index();
}
