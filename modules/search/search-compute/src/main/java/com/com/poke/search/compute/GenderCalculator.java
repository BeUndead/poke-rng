package com.com.poke.search.compute;

import com.com.poke.model.Gender;
import com.com.poke.model.GenderRatio;
import com.com.poke.model.Genders;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@link Gender} from the given {@code contexts}.
 */
final class GenderCalculator implements SearchElementCalculator<Gender> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Gender calculate(
            final ParentsContext parentsContext,
            final TrainerContext trainerContext,
            final MTRng rng
    ) {

        final GenderRatio genderRatio = parentsContext.offspringGenderRatio();

        final Optional<Gender> fixedGenderOptional = genderRatio.fixedGender();
        if (fixedGenderOptional.isPresent()) {
            return fixedGenderOptional.get();
        }

        final OptionalInt thresholdOptional = genderRatio.threshold();
        if (!thresholdOptional.isPresent()) {
            throw new UnsupportedOperationException();
        }

        if (rng.nextInt(252) < thresholdOptional.getAsInt()) {
            return Genders.Female;
        }

        return Genders.Male;
    }
}
