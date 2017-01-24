package com.com.poke.search.compute;


import com.com.poke.search.context.ParentsContext;
import com.com.rng.mt.MTRng;

/**
 * Calculates the {@link InheritanceType} of {@code T}.
 *
 * @param <T> The {@code Type} of the {@code InheritanceType} to calculate.
 */
interface InheritanceTypeCalculator<T> {

    /**
     * Calculate the {@link InheritanceType} of {@code T} in the given {@link ParentsContext} and {@link MTRng rng
     * state}.
     *
     * @param parentsContext The details of the {@code context} for the parents.
     * @param rng            The current state of the {@code rng}.
     * @return The {@code InheritanceType} of {@code T} in the given circumstances.
     */
    InheritanceType<T> calculateInheritaceType(ParentsContext parentsContext, MTRng rng);
}
