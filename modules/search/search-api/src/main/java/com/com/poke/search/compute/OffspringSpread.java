package com.com.poke.search.compute;

import com.com.poke.model.*;

import java.util.OptionalInt;

/**
 * The resultant offspring's details for a given breed.
 */
public interface OffspringSpread {

    /**
     * The {@link AbilitySlot} of the offspring.
     *
     * @return The {@code AbilitySlot} of the offspring.
     */
    AbilitySlot abilitySlot();

    /**
     * The {@link InheritanceType} method for the {@link Ball}.
     *
     * @return The {@code InheritanceType} for the {@code Ball}.
     */
    InheritanceType<Ball> ballInheritance();

    /**
     * The {@link Ball} in which the offspring is contained.
     *
     * @return The {@code Ball} in which the offspring is contained.
     */
    Ball ball();

    /**
     * The {@link Gender} of the offspring.
     *
     * @return The {@code Gender} of the offspring.
     */
    Gender gender();

    /**
     * The {@link InheritanceType} for the {@link IVs} of the offspring.
     *
     * @return The {@code InheritanceType} for the {@code IVs} of the offspring.
     */
    InheritanceType<IVs> ivsInheritance();

    /**
     * A {@link
     *
     * @return
     */
    StatAwareInheritanceType<IV> ivInheritanceForStat(Stat stat);

    /**
     * The {@link IVs} of the offspring.
     *
     * @return The {@code IVs} of the offspring.
     */
    IVs ivs();

    InheritanceType<Nature> natureInheritance();

    /**
     * The {@link Nature} of the offspring.
     *
     * @return The {@code Nature} of the offspring.
     */
    Nature nature();

    /**
     * The {@code pid} of the offspring, if it can be determined.
     *
     * @return The {@code pid} of the offspring as an {@link OptionalInt}; or if it cannot be determined an
     * {@link OptionalInt#empty()}.
     */
    OptionalInt pid();

    /**
     * The {@code encryption constant} of the offspring.
     *
     * @return The {@code encryption constant} of the offspring.
     */
    int encryptionConstant();

    /**
     * The {@code shiny value} of the offspring, if it can be determined.
     *
     * @return The {@code shiny value} of the offspring as an {@link OptionalInt}; or if it cannot be determined an
     * {@link OptionalInt#empty()}.
     */
    default OptionalInt shinyValue() {

        if (!this.pid().isPresent()) {
            return OptionalInt.empty();
        }
        final int pid = this.pid().getAsInt();
        return OptionalInt.of(((pid >>> 0x10) ^ (pid & 0xffff)) >>> 4);
    }
}
