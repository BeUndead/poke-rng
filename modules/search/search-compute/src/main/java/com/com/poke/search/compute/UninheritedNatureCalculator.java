package com.com.poke.search.compute;

import com.com.poke.model.Nature;
import com.com.poke.model.Natures;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@link Nature} initially selected from the given {@code contexts}.  This {@link Nature}
 * will be overridden by that selected by the {@link EverstoneNatureInheritanceTypeCalculator} if either (or both)
 * parent(s) are holding an {@link com.com.poke.model.BreedingItems#Everstone}.
 */
final class UninheritedNatureCalculator implements SearchElementCalculator<Nature> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Nature calculate(ParentsContext parentsContext, TrainerContext trainerContext, MTRng rng) {

        return Natures.values()[rng.nextInt(25)];
    }
}
