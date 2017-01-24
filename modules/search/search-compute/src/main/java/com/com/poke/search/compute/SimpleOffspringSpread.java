package com.com.poke.search.compute;

import com.com.poke.model.*;

import java.util.OptionalInt;

/**
 * Implementation of {@link OffspringSpread}.
 */
final class SimpleOffspringSpread implements OffspringSpread {

    private final AbilitySlot abilitySlot;

    private final InheritanceType<Ball> ballInheritanceType;

    private final Ball ball;

    private final Gender gender;

    private final IVsInheritanceType ivsInheritance;

    private final IVs ivs;

    private final InheritanceType<Nature> natureInheritanceType;

    private final Nature nature;

    private final Integer pid;

    private final int encryptionConstant;

    SimpleOffspringSpread(
            final AbilitySlot abilitySlot,
            final InheritanceType<Ball> ballInheritanceType,
            final Ball ball,
            final Gender gender,
            final IVsInheritanceType ivsInheritance,
            final IVs ivs,
            final InheritanceType<Nature> natureInheritanceType,
            final Nature nature,
            final Integer pid,
            final int encryptionConstant) {

        this.abilitySlot = abilitySlot;
        this.ballInheritanceType = ballInheritanceType;
        this.ball = ball;
        this.gender = gender;
        this.ivsInheritance = ivsInheritance;
        this.ivs = ivs;
        this.natureInheritanceType = natureInheritanceType;
        this.nature = nature;
        this.pid = pid;
        this.encryptionConstant = encryptionConstant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbilitySlot abilitySlot() {

        return this.abilitySlot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<Ball> ballInheritance() {

        return this.ballInheritanceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ball ball() {

        return this.ball;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gender gender() {

        return this.gender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<IVs> ivsInheritance() {

        return this.ivsInheritance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatAwareInheritanceType<IV> ivInheritanceForStat(final Stat stat) {

        return this.ivsInheritance.forStat(stat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<Nature> natureInheritance() {

        return this.natureInheritanceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVs ivs() {

        return this.ivs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nature nature() {

        return this.nature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt pid() {

        return this.pid == null ? OptionalInt.empty() : OptionalInt.of(this.pid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int encryptionConstant() {

        return this.encryptionConstant;
    }
}
