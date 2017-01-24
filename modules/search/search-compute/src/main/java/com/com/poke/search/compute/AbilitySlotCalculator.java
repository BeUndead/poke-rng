package com.com.poke.search.compute;

import com.com.poke.model.AbilitySlot;
import com.com.poke.model.AbilitySlots;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@link AbilitySlot} from the given {@code contexts}.
 */
final class AbilitySlotCalculator implements SearchElementCalculator<AbilitySlot> {

    /**
     * {@inheritDoc}
     */
    @Override
    public AbilitySlot calculate(final ParentsContext parentsContext, TrainerContext trainerContext, final MTRng rng) {

        final int rngCallMod100 = rng.nextInt(100);

        final AbilitySlot relevantParentAbilitySlot;
        if (parentsContext.femaleContext().isDitto()) {
            relevantParentAbilitySlot = parentsContext.maleContext().ability();
        } else {
            relevantParentAbilitySlot = parentsContext.femaleContext().ability();
        }

        if (!(relevantParentAbilitySlot instanceof AbilitySlots)) {
            throw new UnsupportedOperationException();
        }

        final AbilitySlots relevantParentAbilitySlots = (AbilitySlots) relevantParentAbilitySlot;

        switch (relevantParentAbilitySlots) {
            case Slot0:
                if (rngCallMod100 < 80) {
                    return AbilitySlots.Slot0;
                }
                return AbilitySlots.Slot1;

            case Slot1:
                if (rngCallMod100 < 20) {
                    return AbilitySlots.Slot0;
                }
                return AbilitySlots.Slot1;

            case Hidden: // Falls through
            default:
                if (rngCallMod100 < 20) {
                    return AbilitySlots.Slot0;
                } else if (rngCallMod100 < 40) {
                    return AbilitySlots.Slot1;
                }
                return AbilitySlots.Hidden;
        }
    }
}
