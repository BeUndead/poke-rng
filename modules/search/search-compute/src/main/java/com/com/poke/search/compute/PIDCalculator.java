package com.com.poke.search.compute;

import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@code pid} from the given {@code contexts}.
 */
final class PIDCalculator implements SearchElementCalculator<Integer> {

    /**
     * {@inheritDoc}
     * <p>
     * Implementation returns {@code null} to represent the {@code PID} cannot be determined from the given {@code
     * contexts}.
     */
    @Override
    public Integer calculate(
            final ParentsContext parentsContext,
            final TrainerContext trainerContext,
            final MTRng rng
    ) {

        if (!trainerContext.hasShinyCharm() && !parentsContext.satisfyMasudaMethodCriterion()) {
            // Use 'null' to represent unspecified value.
            return null;
        }

        final int maxNumberOfReRolls;
        if (trainerContext.hasShinyCharm()) {
            if (parentsContext.satisfyMasudaMethodCriterion()) {
                maxNumberOfReRolls = 8;
            } else {
                maxNumberOfReRolls = 2;
            }
        } else {
            maxNumberOfReRolls = 6;
        }

        int reRollsPerformed = 0;

        while (reRollsPerformed < maxNumberOfReRolls) {

            final int pidCandidate = rng.nextInt();
            final int psv = ((pidCandidate >>> 0x10) ^ (pidCandidate & 0xffff)) >>> 4;

            if (psv == trainerContext.shinyValue()) {
                // Use the value as the PID if it is a match
                return pidCandidate;
            }
            reRollsPerformed++;
        }

        // Once we've exhausted all re-rolls and found none are shiny, return the last called value.
        return rng.currentInt();
    }
}
