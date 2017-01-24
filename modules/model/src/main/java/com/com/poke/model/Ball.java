package com.com.poke.model;

/**
 * Represents the various {@code Balls} which a {@code Pokémon} may be caught in.
 */
public interface Ball {

    /**
     * Whether or not the specific {@link Balls} can be <strong>inherited</strong> by offspring.
     *
     * @return {@code true} if the {@code Balls} <i>can</i> be inherited; otherwise {@code false}.
     */
    default boolean canBeInherited() {

        return true;
    }
}
