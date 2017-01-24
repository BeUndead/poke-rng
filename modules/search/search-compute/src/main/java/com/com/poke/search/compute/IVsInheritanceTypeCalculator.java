package com.com.poke.search.compute;

import com.com.poke.model.*;
import com.com.poke.search.context.ParentsContext;
import com.com.rng.mt.MTRng;

/**
 * {@inheritDoc}
 * <p>
 * Implementation calculates the {@link InheritanceType} for the {@link IVs}.
 */
final class IVsInheritanceTypeCalculator implements InheritanceTypeCalculator<IVs> {

    private static final int IVS_TO_INHERIT_WITHOUT_DESTINY_KNOT = 3;

    private static final int IVS_TO_INHERIT_WITH_DESTINY_KNOT = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public InheritanceType<IVs> calculateInheritaceType(ParentsContext parentsContext, MTRng rng) {

        final boolean destinyKnot =
                parentsContext.maleContext().heldItem() == BreedingItems.DestinyKnot ||
                        parentsContext.femaleContext().heldItem() == BreedingItems.DestinyKnot;

        final int numberOfIVsToInherit = destinyKnot
                ? IVS_TO_INHERIT_WITH_DESTINY_KNOT : IVS_TO_INHERIT_WITHOUT_DESTINY_KNOT;

        final StatAwareInheritanceType<IV>[] inheritanceTypes = new IVInheritanceType[6];

        final BreedingItem maleItem = parentsContext.maleContext().heldItem();
        final BreedingItem femaleItem = parentsContext.femaleContext().heldItem();

        int numberOfInheritedIVs = 0;

        // Consider Power Items
        if (this.isPowerItem(maleItem)) {
            final Stat statToInherit = this.getStatFromPowerItem(maleItem);
            final StatAwareInheritanceType<IV> inheritanceType;
            if (maleItem == femaleItem) {
                final int rngCall = rng.nextInt(100);
                if (rngCall < 50) {
                    inheritanceType = IVInheritanceType.Male;
                } else {
                    inheritanceType = IVInheritanceType.Female;
                }
            } else {
                inheritanceType = IVInheritanceType.Male;
            }

            inheritanceTypes[statToInherit.index()] = inheritanceType;
            numberOfInheritedIVs++;
        }
        if (femaleItem != maleItem && this.isPowerItem(femaleItem)) {
            final Stat statToInherit = this.getStatFromPowerItem(femaleItem);
            inheritanceTypes[statToInherit.index()] = IVInheritanceType.Female;
            numberOfInheritedIVs++;
        }

        while (numberOfInheritedIVs < numberOfIVsToInherit) {
            // One call to choose a stat.
            final Stat stat = Stats.values()[rng.nextInt(6)];

            if (inheritanceTypes[stat.index()] != null) {

                // InheritanceType for stat already determined; ignore call and try again.
                continue;
            }

            // Select a parent and set the inheritance for the stat.
            final StatAwareInheritanceType<IV> parent = IVInheritanceType.values()[rng.nextInt(2)];
            inheritanceTypes[stat.index()] = parent;
            numberOfInheritedIVs++;
        }

        // Random IV genderated for each stat.
        for (final Stat stat : Stats.values()) {
            final StatAwareInheritanceType<IV> randomIVForStat = IVInheritanceType.values()[rng.nextInt(32) + 2];
            if (inheritanceTypes[stat.index()] == null) {
                inheritanceTypes[stat.index()] = randomIVForStat;
            }
        }

        return new IVsInheritanceType(inheritanceTypes);
    }

    /**
     * Determines whether or not the given {@link BreedingItem} is a 'Power' item.
     *
     * @param item The {@code item} to check.
     * @return {@code true} if the given {@code item} is one of: <ul> <li>{@link BreedingItems#PowerAnklet},</li>
     * <li>{@link BreedingItems#PowerBand},</li> <li>{@link BreedingItems#PowerBelt},</li> <li>{@link
     * BreedingItems#PowerBracer},</li> <li>{@link BreedingItems#PowerLens}, or</li> <li>{@link
     * BreedingItems#PowerWeight};</li> </ul> otherwise {@code false}.
     */
    private boolean isPowerItem(final BreedingItem item) {

        return item == BreedingItems.PowerAnklet
                || item == BreedingItems.PowerBand
                || item == BreedingItems.PowerBelt
                || item == BreedingItems.PowerBracer
                || item == BreedingItems.PowerLens
                || item == BreedingItems.PowerWeight;

    }

    /**
     * Determines the {@link Stat} to which the given {@code powerItem} pertains.
     *
     * @param powerItem The relevant {@code powerItem}.
     * @return The {@link Stat} to which the given {@code powerItem} pertains.
     */
    private Stat getStatFromPowerItem(final BreedingItem powerItem) {

        if (!this.isPowerItem(powerItem)) {
            throw new IllegalArgumentException("'powerItem' must be a 'Power' BreedingItem");
        }

        final BreedingItems itemAsBreedingItems = (BreedingItems) powerItem;

        // Can be replaced with:
//        return Stats.values()[itemAsBreedingItems.ordinal() - 3];
        switch (itemAsBreedingItems) {
            case PowerWeight:
                return Stats.HP;
            case PowerBracer:
                return Stats.Attack;
            case PowerBelt:
                return Stats.Defense;
            case PowerLens:
                return Stats.SpecialAttack;
            case PowerBand:
                return Stats.SpecialDefense;
            case PowerAnklet:
                return Stats.Speed;

            default:
                throw new IllegalStateException();
        }
    }
}
