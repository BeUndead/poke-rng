package com.com.poke.search.context;

import com.com.poke.model.*;

/**
 * Context for an individual parent.
 */
public interface IndividualParentContext {

    /**
     * The {@link BreedingItem item} being held by this parent.
     *
     * @return The {@code item} being held by this parent.
     */
    BreedingItem heldItem();

    /**
     * The {@link AbilitySlot} of this parent.
     *
     * @return The {@code AbilitySlot} of this parent.
     */
    AbilitySlot ability();

    /**
     * The {@link IVs} of this parent.
     *
     * @return The {@code IVs} of this parent.
     */
    IVs ivs();

    /**
     * The {@link Nature} of this parent.
     *
     * @return The {@code Nature} of this parent.
     */
    Nature nature();

    /**
     * The {@link Ball} in which this parent is contained.
     *
     * @return The {@code Ball} in which this parent is contained.
     */
    Ball ball();

    /**
     * Whether or not this parent is a {@code Ditto}.
     *
     * @return {@code true} if this parent <strong>is</strong> a {@code Ditto}; otherwise {@code false}.
     */
    boolean isDitto();
}
