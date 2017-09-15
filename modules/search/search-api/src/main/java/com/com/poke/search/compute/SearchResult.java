package com.com.poke.search.compute;

import com.com.rng.mt.MTRng;

/**
 * The result of a search.
 */
public interface SearchResult {

    /**
     * The {@link OffspringSpread} for this {@link SearchResult}.
     *
     * @return The {@code OffspringSpread} for this {@code SearchResult}.
     */
    OffspringSpread offspringSpread();

    /**
     * The (equivalent) seed of this {@link SearchResult}.
     *
     * @return The seed for this {@code SearchResult}.
     */
    int[] initialSeed();

    /**
     * The number of frames advanced from the initial {@link MTRng} provided to the context at which this
     * {@link SearchResult} is generated.
     *
     * @return The number of frames advanced from the initial {@code rng} at which this {@code SearchResult} is
     * generated.
     */
    long frameFromInitial();

    /**
     * The number of {@link MTRng} advancements used in the generation of this {@link SearchResult}.
     *
     * @return The number of {@code rng} advancements used in the generation of this {@code SearchResult}.
     */
    long rngAdvancements();
}
