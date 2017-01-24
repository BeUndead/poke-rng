package com.com.poke.model;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents the various {@link GenderRatios} that a {@code Pokémon} species may have.
 */
public interface GenderRatio {

    /**
     * The {@code threshold} (out of {@code 252}) for the {@link Genders#Male} {@link Genders#Female} split for this
     * {@link GenderRatio}.
     *
     * @return An {@link OptionalInt} of the {@code Male}/{@code Female} {@link Gender} threshold if one exists.
     * Otherwise (for {@link #fixedGender()} species) {@link OptionalInt#empty()}.
     */
    OptionalInt threshold();

    /**
     * The {@link Gender} which this {@link GenderRatio} is fixed to.
     *
     * @return An {@link Optional} {@code Gender} of the fixed {@code Gender} for this {@code GenderRatio} if this
     * {@code GenderRatio} is fixed.  Otherwise {@link Optional#empty()}.
     */
    Optional<Gender> fixedGender();
}
