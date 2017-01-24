package com.com.poke.search.compute;


import com.com.poke.search.context.ParentsContext;

/**
 * Interface to describe the manner in which elements of {@code T} are inherited from the parents.
 *
 * @param <T> The {@code Type} of the item being inherited.
 */
public interface InheritanceType<T> {

    /**
     * Generates the item (of {@code Type} {@code T}) which this {@link InheritanceType} would result in, for the given
     * {@link ParentsContext}.
     *
     * @param parentsContext The details of the {@code Context} for the parents.
     * @return The {@code T} which this {@code InheritanceType} specifies should be inherited, for the given {@code
     * ParentsContext}.
     */
    T determineInheritance(ParentsContext parentsContext);
}
