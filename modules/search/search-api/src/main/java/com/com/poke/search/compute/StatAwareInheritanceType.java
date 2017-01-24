package com.com.poke.search.compute;

import com.com.poke.model.Stat;
import com.com.poke.search.context.ParentsContext;

/**
 * Interface to describe the manner in which {@code inheritance} should be performed.
 * <p>
 * Like {@link InheritanceType}, except requires awareness of what {@link Stat} it pertains to.
 *
 * @param <T> The {@code Type} of element which this implementation describes the inheritance for.
 */
public interface StatAwareInheritanceType<T> {

    /**
     * Determines the {@link T} that this {@link StatAwareInheritanceType} determines as inherited for the given
     * {@link Stat}, with the given {@code parentsContext}.
     *
     * @param parentsContext The details of the {@code context} for the parents.
     * @param stat           The {@code stat} to compute the value of {@code T} for.
     * @return The value of {@code T} which is to be inherited (or not) from the given {@code parentsContext}, for the
     * given {@code Stat}.
     */
    T determineInheritance(ParentsContext parentsContext, Stat stat);
}
