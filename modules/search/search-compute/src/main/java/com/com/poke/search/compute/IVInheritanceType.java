package com.com.poke.search.compute;

import com.com.poke.model.CollectiveIVs;
import com.com.poke.model.IV;
import com.com.poke.model.IndividualIV;
import com.com.poke.model.Stat;
import com.com.poke.search.context.ParentsContext;

/**
 * Represents the {@code inheritance} of a <strong>single</strong> {@link CollectiveIVs IV}.
 */
enum IVInheritanceType implements StatAwareInheritanceType<IV> {

    /**
     * The {@link IV} is inherited from the {@link com.com.poke.model.Genders#Male} parent.
     */
    Male {
        /**
         * {@inheritDoc}
         */
        @Override
        public IV determineInheritance(final ParentsContext parentsContext, final Stat stat) {

            return parentsContext.maleContext().ivs().forStat(stat);
        }
    },

    /**
     * The {@link IV} is inherited from the {@link com.com.poke.model.Genders#Female} parent.
     */
    Female {
        /**
         * {@inheritDoc}
         */
        @Override
        public IV determineInheritance(final ParentsContext parentsContext, final Stat stat) {

            return parentsContext.femaleContext().ivs().forStat(stat);
        }
    },

    // Various fixed values.
    FIXED_00,
    FIXED_01,
    FIXED_02,
    FIXED_03,
    FIXED_04,
    FIXED_05,
    FIXED_06,
    FIXED_07,
    FIXED_08,
    FIXED_09,
    FIXED_10,
    FIXED_11,
    FIXED_12,
    FIXED_13,
    FIXED_14,
    FIXED_15,
    FIXED_16,
    FIXED_17,
    FIXED_18,
    FIXED_19,
    FIXED_20,
    FIXED_21,
    FIXED_22,
    FIXED_23,
    FIXED_24,
    FIXED_25,
    FIXED_26,
    FIXED_27,
    FIXED_28,
    FIXED_29,
    FIXED_30,
    FIXED_31;

    /**
     * {@inheritDoc}
     */
    @Override
    public IV determineInheritance(final ParentsContext parentsContext, final Stat stat) {

        return IndividualIV.of((byte) (this.ordinal() - 2));
    }
}
