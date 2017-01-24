package com.com.poke.model;

/**
 * Represents a {@code Nature} which a particular {@code Pokémon} can have.
 */
public interface Nature {

    /**
     * The {@link Stat} which is <strong>boosted</strong> by this {@link Nature}.  Unless {@code boosted()} and
     * {@link #hindered()} match, this stat receives an {@code 1.1} × multiplier to the stat value for the specified
     * {@code Stat}.
     *
     * @return The boosted {@code Stat}.
     */
    Stat boosted();

    /**
     * The {@link Stat} which is <strong>hindered</strong> by this {@link Nature}.  Unless {@link #boosted()} and
     * {@code hindered()} match, this stat receives an {@code 0.9} × multiplier to the stat value for the specified
     * {@code Stat}.
     *
     * @return The boosted {@code Stat}.
     */
    Stat hindered();

    /**
     * The internal, in-game {@code index} for this {@link Nature}.
     *
     * @return The in-game {@code index} for this {@code Nature}.
     */
    int index();
}
