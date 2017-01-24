package com.com.poke.search.compute;

import com.com.poke.model.Stat;

/**
 * Indicates that the class can return a {@code T} for each {@link Stat}.
 *
 * @param <T> The {@code Type} of the {@code item} which can be returned for each {@code Stat}.
 */
interface StatAware<T> {

    /**
     * Finds the {@code T} which this {@link StatAware} {@code implementation} determines relevant for the given
     * {@link Stat}.
     *
     * @param stat The {@code Stat} to return the {@code T} for.
     * @return The {@code T} which this {@code StatAware} determines relevant for the given {@code Stat}.
     */
    T forStat(final Stat stat);
}
