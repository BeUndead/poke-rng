package com.com.poke.search.compute;

import com.com.poke.model.Ball;
import com.com.poke.search.context.IndividualParentContext;
import com.com.poke.search.context.ParentsContext;
import com.com.rng.mt.MTRng;

/**
 * {@link InheritanceTypeCalculator} for {@link Ball} {@link InheritanceType}.
 */
final class BallInheritanceTypeCalculator implements InheritanceTypeCalculator<Ball> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<Ball> calculateInheritaceType(final ParentsContext parentsContext, final MTRng rng) {

        final IndividualParentContext maleContext = parentsContext.maleContext();
        final IndividualParentContext femaleContext = parentsContext.femaleContext();

        if (maleContext.isDitto() && femaleContext.isDitto()) {
            throw new IllegalArgumentException("Two Dittos will not produce an offspring");
        }

        if (maleContext.isDitto()) {
            // Female is not a Ditto
            if (femaleContext.ball().canBeInherited()) {
                return BallInheritanceTypes.Female;
            } else {
                return BallInheritanceTypes.NotInherited;
            }
        }

        if (femaleContext.isDitto()) {
            if (maleContext.ball().canBeInherited()) {
                return BallInheritanceTypes.Male;
            } else {
                return BallInheritanceTypes.NotInherited;
            }
        }

        // Neither is Ditto...
        if (!parentsContext.areSameFamilyLine()) {
            if (femaleContext.ball().canBeInherited()) {
                return BallInheritanceTypes.Female;
            } else {
                return BallInheritanceTypes.NotInherited;
            }
        }

        final int rngCallMod100 = rng.nextInt(100);
        if (rngCallMod100 < 50) {
            if (maleContext.ball().canBeInherited()) {
                return BallInheritanceTypes.Male;
            } else {
                return BallInheritanceTypes.NotInherited;
            }
        } else {
            if (femaleContext.ball().canBeInherited()) {
                return BallInheritanceTypes.Female;
            } else {
                return BallInheritanceTypes.NotInherited;
            }
        }
    }
}
