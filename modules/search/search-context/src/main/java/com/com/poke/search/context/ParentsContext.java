package com.com.poke.search.context;

import com.com.poke.model.GenderRatio;

/**
 * Context for the collective parents involved in a breed.
 */
public interface ParentsContext {

    /**
     * The {@link IndividualParentContext} for the {@link com.com.poke.model.Genders#Male} parent.
     *
     * @return The {@code IndividualParentContext} for the {@code Male} parent.
     */
    IndividualParentContext maleContext();

    /**
     * The {@link IndividualParentContext} for the {@link com.com.poke.model.Genders#Female} parent.
     *
     * @return The {@code IndividualParentContext} for the {@code Female} parent.
     */
    IndividualParentContext femaleContext();

    /**
     * The {@link GenderRatio} of the offspring which these parents produce.
     *
     * @return The {@code GenderRatio} of the offspring which these parents produce.
     */
    GenderRatio offspringGenderRatio();

    /**
     * Whether or not these parents satisfy the <strong>Masuda Method Criterion</strong>.
     *
     * @return {@code true} if the two parents are from different language saves; otherwise {@code false}.
     */
    boolean satisfyMasudaMethodCriterion();

    /**
     * Whether or not these parents are from the same {@code Pokémon} species family line.
     *
     * @return {@code true} if the two parents are from the same family line; otherwise {@code false}.
     */
    boolean areSameFamilyLine();
}
