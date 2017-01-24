package com.com.poke.search.compute;

import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@code encryption constant} from the given {@code contexts}.
 */
final class EncryptionConstantCalculator implements SearchElementCalculator<Integer> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer calculate(ParentsContext parentsContext, TrainerContext trainerContext, MTRng rng) {
        // Nothing fancy here; just looks for the next call.
        return rng.nextInt();
    }
}
