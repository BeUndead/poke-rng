package com.com.poke.search.compute;

import com.com.poke.model.*;

/**
 * Helper class providing {@code generic} access to the various implemented {@link SearchElementCalculator
 * SpreadElementCalculators}.
 */
final class Calculators {

    private static final SearchElementCalculator<AbilitySlot> ABILITY_SLOT_CALCULATOR = new AbilitySlotCalculator();

    private static final SearchElementCalculator<Gender> GENDER_CALCULATOR = new GenderCalculator();

    private static final SearchElementCalculator<Nature> UNINHERITED_NATURE_CALCULATOR =
            new UninheritedNatureCalculator();

    private static final InheritanceTypeCalculator<Nature> EVERSTONE_NATURE_CALCULATOR = new
            EverstoneNatureInheritanceTypeCalculator();

    private static final InheritanceTypeCalculator<IVs> IVS_INHERITANCE_CALCULATOR =
            new IVsInheritanceTypeCalculator();

    private static final SearchElementCalculator<Integer> PID_CALCULATOR = new PIDCalculator();

    private static final SearchElementCalculator<Integer> ENCRYPTION_CONSTANT_CALCULATOR =
            new EncryptionConstantCalculator();

    private static final InheritanceTypeCalculator<Ball> BALL_INHERITANCE_TYPE_CALCULATOR =
            new BallInheritanceTypeCalculator();

    private static final IVsCalculator IVS_CALCULATOR = new IVsCalculator();

    private Calculators() {

        throw new IllegalStateException("'com.com.poke.computation.Calculators' is a utility class; no instances");
    }

    /**
     * The {@link SearchElementCalculator} for the {@link AbilitySlot}.
     *
     * @return The {@link SearchElementCalculator} for the {@code AbilitySlot}.
     */
    static SearchElementCalculator<AbilitySlot> abilitySlotCalculator() {

        return Calculators.ABILITY_SLOT_CALCULATOR;
    }

    /**
     * The {@link SearchElementCalculator} for the {@link Gender}.
     *
     * @return The {@link SearchElementCalculator} for the {@code Gender}.
     */
    static SearchElementCalculator<Gender> genderCalculator() {

        return Calculators.GENDER_CALCULATOR;
    }

    /**
     * The {@link SearchElementCalculator} for the {@link Nature} as initially computed.
     *
     * @return The {@link SearchElementCalculator} for the {@code Nature}.
     */
    static SearchElementCalculator<Nature> uninheritedNatureCalculator() {

        return Calculators.UNINHERITED_NATURE_CALCULATOR;
    }

    /**
     * The {@link InheritanceTypeCalculator} for the {@link Nature} if an {@link BreedingItems#Everstone} is held by
     * either (or both) parents.
     *
     * @return The {@link } for the {@code Nature}.
     */
    static InheritanceTypeCalculator<Nature> everstoneNatureCalculator() {

        return Calculators.EVERSTONE_NATURE_CALCULATOR;
    }

    /**
     * The {@link InheritanceTypeCalculator} of the {@link InheritanceType} for the {@link IVs}.
     *
     * @return The {@code InheritanceTypeCalculator} for the {@code InheritanceType}.
     */
    static InheritanceTypeCalculator<IVs> ivsInheritanceCalculator() {

        return Calculators.IVS_INHERITANCE_CALCULATOR;
    }

    /**
     * The {@link SearchElementCalculator} for the {@code pid}.
     *
     * @return The {@code SearchElementCalculator} for the {@code pid}.
     */
    static SearchElementCalculator<Integer> pidCalculator() {

        return Calculators.PID_CALCULATOR;
    }

    /**
     * The {@link SearchElementCalculator} for the {@code encryption constant}.
     *
     * @return The {@code SearchElementCalculator} for the {@code encryption constant}.
     */
    static SearchElementCalculator<Integer> encryptionConstantCalculator() {

        return Calculators.ENCRYPTION_CONSTANT_CALCULATOR;
    }

    /**
     * The {@link InheritanceTypeCalculator} for the {@link Ball}.
     *
     * @return The {@link InheritanceTypeCalculator} for the {@code Ball}.
     */
    static InheritanceTypeCalculator<Ball> ballCalculator() {

        return Calculators.BALL_INHERITANCE_TYPE_CALCULATOR;
    }

    /**
     * A calculator for the {@link IVs}.  Note: This does <i>not</i> implement {@link SearchElementCalculator}.
     * The {@code IVsInheritanceType} found using the {@link #ivsInheritanceCalculator()} should be used in this to
     * performSearch the {@code IVs}.
     *
     * @return A calculator for the {@link IVs}.
     */
    static IVsCalculator ivsCalculator() {

        return Calculators.IVS_CALCULATOR;
    }
}
