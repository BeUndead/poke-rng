package com.com.poke.model;

/**
 * Represents the {@link IV IVs} of a {@code Pokémon}.
 */
public interface IVs {

    /**
     * Returns the {@link IV} for the requested {@link Stat}.
     *
     * @param stat The {@code Stat} to return the {@code IV} of.
     * @return The {@code IV} for the provided {@code Stat}.
     */
    IV forStat(final Stat stat);

    /**
     * Returns the {@link Type hidden power type} of these {@link IVs}.
     *
     * @return The {@code hidden power type} of these {@code IVs}.
     */
    Type hiddenPower();
}
