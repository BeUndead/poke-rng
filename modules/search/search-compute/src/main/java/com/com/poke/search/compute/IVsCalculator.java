package com.com.poke.search.compute;

import com.com.poke.model.*;
import com.com.poke.search.context.ParentsContext;

/**
 * Calculator for the {@link IVs} given the {@link StatAwareInheritanceType} of all the {@link IV IVs}.
 */
final class IVsCalculator {

    /**
     * Calculates the explicit {@link IVs} for the given {@link ParentsContext}; assuming that they are to be inherited
     * under the description of the given {@code ivsInheritance}.
     *
     * @param parentsContext The details of the {@code context} for the parents.
     * @param ivsInheritance {@link StatAware} for the {@link StatAwareInheritanceType InheritanceType} of each {@code
     *                       IV}.
     * @return The {@code IVs} which the given {@code ivsInheritance} dictates, in the given {@code context}.
     */
    IVs calculate(final ParentsContext parentsContext,
                  final StatAware<StatAwareInheritanceType<IV>> ivsInheritance) {

        // Build the IVs from the inheritance
        final byte[] ivs = new byte[6];
        for (final Stat stat : Stats.values()) {
            final StatAwareInheritanceType<IV> inheritanceForIV = ivsInheritance.forStat(stat);
            ivs[stat.index()] = inheritanceForIV.determineInheritance(parentsContext, stat).value();
        }

        return CollectiveIVs.from(ivs);
    }
}
