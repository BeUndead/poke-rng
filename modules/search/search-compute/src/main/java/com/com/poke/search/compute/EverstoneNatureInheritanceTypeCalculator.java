package com.com.poke.search.compute;

import com.com.poke.model.BreedingItems;
import com.com.poke.model.Genders;
import com.com.poke.model.Nature;
import com.com.poke.search.context.ParentsContext;
import com.com.rng.mt.MTRng;

import java.util.Objects;

/**
 * Calculates the {@link InheritanceType} for the {@link Nature}.
 */
final class EverstoneNatureInheritanceTypeCalculator implements InheritanceTypeCalculator<Nature> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<Nature> calculateInheritaceType(final ParentsContext parentsContext, final MTRng rng) {

        final boolean maleHoldingEverstone = parentsContext.maleContext().heldItem() == BreedingItems.Everstone;
        final boolean femaleHoldingEverstone = parentsContext.femaleContext().heldItem() == BreedingItems.Everstone;

        if (!maleHoldingEverstone && !femaleHoldingEverstone) {
            throw new IllegalArgumentException("At least one parent should be holding an 'Everstone'");
        }

        final Genders whichParent;
        if (maleHoldingEverstone ^ femaleHoldingEverstone) {
            whichParent = maleHoldingEverstone ? Genders.Male : Genders.Female;
        } else {
            whichParent = Genders.values()[rng.nextInt(2)];
        }

        switch (whichParent) {
            case Male:
                return NatureInheritanceType.Male;
            case Female:
                return NatureInheritanceType.Female;

            default:
                throw new IllegalStateException("Unexpected Gender: " + Objects.toString(whichParent));
        }
    }
}
