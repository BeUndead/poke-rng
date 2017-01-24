package com.com.poke.model;

/**
 * Enumeration of all the various {@code Stats} for {@code Pokémon}.
 */
public enum Stats implements Stat {

    /**
     * Hit-Points; the health of the {@code Pokémon}.
     */
    HP,

    /**
     * Attack; used in the computation of damage dealt by physical moves.
     */
    Attack,

    /**
     * Defense; used in the computation of damage received by physical moves.
     */
    Defense,

    /**
     * Special Attack; used in the computation of damage dealt by special moves.
     */
    SpecialAttack,

    /**
     * Special Defense; used in the computation of damage received by special moves.
     */
    SpecialDefense,

    /**
     * Speed; used in determining the order in which {@code Pokémon} will take their turns.
     */
    Speed;

    /**
     * {@inheritDoc}
     */
    @Override
    public int index() {

        return this.ordinal();
    }
}
