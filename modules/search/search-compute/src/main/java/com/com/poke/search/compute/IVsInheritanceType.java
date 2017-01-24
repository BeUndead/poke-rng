package com.com.poke.search.compute;

import com.com.poke.model.*;
import com.com.poke.search.context.ParentsContext;

/**
 * {@inheritDoc}
 * <p>
 * Container class to provide access to all {@link IVInheritanceType IVInheritances} for the various {@link Stat Stats}
 * of a {@code Pokémon}.
 */
final class IVsInheritanceType implements InheritanceType<IVs>, StatAware<StatAwareInheritanceType<IV>> {

    private final StatAwareInheritanceType<IV>[] inheritances;

    IVsInheritanceType(final StatAwareInheritanceType<IV>[] inheritances) {

        if (inheritances == null || inheritances.length != 6) {
            throw new IllegalArgumentException("'inheritances' must have length 6");
        }
        for (final StatAwareInheritanceType<IV> inheritance : inheritances) {
            if (inheritance == null) {
                throw new IllegalArgumentException("'inheritances' must not contain any 'null's");
            }
        }

        this.inheritances = inheritances;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVs determineInheritance(final ParentsContext parentsContext) {

        final byte[] ivs = new byte[6];
        for (final Stat stat : Stats.values()) {
            this.forStat(stat).determineInheritance(parentsContext, stat);
        }
        return CollectiveIVs.from(ivs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatAwareInheritanceType<IV> forStat(Stat stat) {

        return this.inheritances[stat.index()];
    }
}
