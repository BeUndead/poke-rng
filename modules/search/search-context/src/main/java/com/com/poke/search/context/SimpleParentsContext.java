package com.com.poke.search.context;

import com.com.poke.model.GenderRatio;

/**
 * Implementation of {@link ParentsContext}.
 */
public final class SimpleParentsContext implements ParentsContext {

    private final IndividualParentContext maleContext;

    private final IndividualParentContext femaleContext;

    private final GenderRatio genderRatio;

    private final boolean satisfyMasudaMethodCriterion;

    private final boolean areSameFamilyLine;

    public SimpleParentsContext(
            final IndividualParentContext maleContext,
            final IndividualParentContext femaleContext,
            final GenderRatio genderRatio,
            final boolean satisfyMasudaMethodCriterion,
            final boolean areSameFamilyLine) {

        this.maleContext = maleContext;
        this.femaleContext = femaleContext;
        this.genderRatio = genderRatio;
        this.satisfyMasudaMethodCriterion = satisfyMasudaMethodCriterion;
        this.areSameFamilyLine = areSameFamilyLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndividualParentContext maleContext() {

        return this.maleContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndividualParentContext femaleContext() {

        return this.femaleContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenderRatio offspringGenderRatio() {

        return this.genderRatio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean satisfyMasudaMethodCriterion() {

        return this.satisfyMasudaMethodCriterion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areSameFamilyLine() {

        return this.areSameFamilyLine;
    }
}
