package com.com.poke.model;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Provides an implementation of {@link GenderRatio} for all of the {@link GenderRatios} presently in use in the game.
 */
public enum GenderRatios implements GenderRatio {

    /**
     * Represents species which are <strong>always</strong> {@link Genders#Female}..
     */
    AlwaysFemale(Genders.Female),

    //    /**
    //     * Represents species which are <strong>usually</strong> {@link Genders#Female} ({@code Female} :
    //     * {@link Genders#Male} ratio of 7 : 1).
    //     */
    //    UsuallyFemale(false),

    /**
     * Represents species which are <strong>mostly</strong>. {@link Genders#Female} ({@code Female} :
     * {@link Genders#Male} ratio of 3 : 1).
     */
    LikelyFemale(189),

    /**
     * Represents species which are equally both non-{@link Genders#Genderless} {@link Gender Genders}
     * ({@link Genders#Female} : {@link Genders#Male} ratio of 1 : 1).
     */
    Even(126),

    /**
     * Respresents species which are <strong>mostly</strong> {@link Genders#Male} ({@link Genders#Female} :
     * {@code Male} ratio of 1 : 3).
     */
    LikelyMale(63),

    /**
     * Represents species which are <strong>usually</strong> {@link Genders#Male} (@{@link Genders#Female} :
     * {@code Male} ratio of 1 : 7).
     */
    UsuallyMale(30),

    /**
     * Represents species which are <strong>always</strong> {@link Genders#Male}.
     */
    AlwaysMale(Genders.Male),

    /**
     * Represents species which are <strong>always</strong> {@link Genders#Genderless}.
     */
    Genderless(Genders.Genderless);

    private static final int INVALID_THRESHOLD = Integer.MIN_VALUE;

    private final int threshold;

    private final Gender fixedGender;

    GenderRatios(final int threshold) {

        this.threshold = threshold;
        this.fixedGender = null;
    }

    GenderRatios(final Gender fixedGender) {

        this.threshold = INVALID_THRESHOLD;
        this.fixedGender = fixedGender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt threshold() {

        if (this.threshold == GenderRatios.INVALID_THRESHOLD) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(this.threshold);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Gender> fixedGender() {

        return Optional.ofNullable(fixedGender);
    }
}
