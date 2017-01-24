package com.com.poke.search.context;

import com.com.poke.model.*;

/**
 * Implementation of {@link IndividualParentContext}.
 */
public final class SimpleIndividualParentContext implements IndividualParentContext {

    private final BreedingItem heldItem;

    private final AbilitySlot abilitySlot;

    private final IVs ivs;

    private final Nature nature;

    private final Ball ball;

    private final boolean isDitto;

    public SimpleIndividualParentContext(
            final BreedingItem heldItem,
            final AbilitySlot abilitySlot,
            final IVs ivs,
            final Nature nature,
            final Ball ball,
            final boolean isDitto) {

        this.heldItem = heldItem;
        this.abilitySlot = abilitySlot;
        this.ivs = ivs;
        this.nature = nature;
        this.ball = ball;
        this.isDitto = isDitto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BreedingItem heldItem() {

        return this.heldItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbilitySlot ability() {

        return this.abilitySlot;
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
    public Ball ball() {

        return this.ball;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDitto() {

        return this.isDitto;
    }
}
