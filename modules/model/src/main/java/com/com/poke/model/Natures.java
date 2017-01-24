package com.com.poke.model;

/**
 * {@link Enum} representing the various {@link Natures Natures} which a Pokémon can have.
 */
public enum Natures implements Nature {

    /**
     * Neutral {@link Nature}.
     */
    Hardy(Stats.Attack, Stats.Attack),
    /**
     * Raises {@link Stats#Attack} and lowers {@link Stats#Defense}.
     */
    Lonely(Stats.Attack, Stats.Defense),
    /**
     * Raises {@link Stats#Attack} and lowers {@link Stats#Speed}.
     */
    Brave(Stats.Attack, Stats.Speed),
    /**
     * Raises {@link Stats#Attack} and lowers {@link Stats#SpecialAttack}.
     */
    Adamant(Stats.Attack, Stats.SpecialAttack),
    /**
     * Raises {@link Stats#Attack} and lowers {@link Stats#SpecialDefense}.
     */
    Naughty(Stats.Attack, Stats.SpecialDefense),

    /**
     * Raises {@link Stats#Defense} and lowers {@link Stats#Attack}.
     */
    Bold(Stats.Defense, Stats.Attack),
    /**
     * Neutral {@link Nature}.
     */
    Docile(Stats.Defense, Stats.Defense),
    /**
     * Raises {@link Stats#Defense} and lowers {@link Stats#Speed}.
     */
    Relaxed(Stats.Defense, Stats.Speed),
    /**
     * Raises {@link Stats#Defense} and lowers {@link Stats#SpecialAttack}.
     */
    Impish(Stats.Defense, Stats.SpecialAttack),
    /**
     * Raises {@link Stats#Defense} and lowers {@link Stats#SpecialDefense}.
     */
    Lax(Stats.Defense, Stats.SpecialDefense),

    /**
     * Raises {@link Stats#Speed} and lowers {@link Stats#Attack}.
     */
    Timid(Stats.Speed, Stats.Attack),
    /**
     * Raises {@link Stats#Speed} and lowers {@link Stats#Defense}.
     */
    Hasty(Stats.Speed, Stats.Defense),
    /**
     * Neutral {@link Nature}.
     */
    Serious(Stats.Speed, Stats.Speed),
    /**
     * Raises {@link Stats#Speed} and lowers {@link Stats#SpecialAttack}.
     */
    Jolly(Stats.Speed, Stats.SpecialAttack),
    /**
     * Raises {@link Stats#Speed} and lowers {@link Stats#SpecialDefense}.
     */
    Naive(Stats.Speed, Stats.SpecialDefense),

    /**
     * Raises {@link Stats#SpecialAttack} and lowers {@link Stats#Attack}.
     */
    Modest(Stats.SpecialAttack, Stats.Attack),
    /**
     * Raises {@link Stats#SpecialAttack} and lowers {@link Stats#Defense}.
     */
    Mild(Stats.SpecialAttack, Stats.Defense),
    /**
     * Raises {@link Stats#SpecialAttack} and lowers {@link Stats#Speed}.
     */
    Quiet(Stats.SpecialAttack, Stats.Speed),
    /**
     * Neutral {@link Nature}.
     */
    Bashful(Stats.SpecialAttack, Stats.SpecialAttack),
    /**
     * Raises {@link Stats#SpecialAttack} and lowers {@link Stats#SpecialDefense}.
     */
    Rash(Stats.SpecialAttack, Stats.SpecialDefense),

    /**
     * Raises {@link Stats#SpecialDefense} and lowers {@link Stats#Attack}.
     */
    Calm(Stats.SpecialDefense, Stats.Attack),
    /**
     * Raises {@link Stats#SpecialDefense} and lowers {@link Stats#Defense}.
     */
    Gentle(Stats.SpecialDefense, Stats.Defense),
    /**
     * Raises {@link Stats#SpecialDefense} and lowers {@link Stats#Speed}.
     */
    Sassy(Stats.SpecialDefense, Stats.Speed),
    /**
     * Raises {@link Stats#SpecialDefense} and lowers {@link Stats#SpecialAttack}.
     */
    Careful(Stats.SpecialDefense, Stats.SpecialAttack),
    /**
     * Neutral {@link Nature}.
     */
    Quirky(Stats.SpecialDefense, Stats.SpecialDefense);

    private final Stats boosted;

    private final Stats hindered;

    Natures(final Stats boosted, final Stats hindered) {

        this.boosted = boosted;
        this.hindered = hindered;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stat boosted() {

        return this.boosted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stat hindered() {

        return this.hindered;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int index() {

        return this.ordinal();
    }
}
