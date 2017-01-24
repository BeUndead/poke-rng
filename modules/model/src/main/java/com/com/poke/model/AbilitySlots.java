package com.com.poke.model;

/**
 * Convenience {@code Enum} to identify which of a {@code Pokémon's} {@code AbilitySlots} it has.
 */
public enum AbilitySlots implements AbilitySlot {

    /**
     * The first, non-hidden {@code Ability} a {@code Pokémon} may have.
     */
    Slot0,

    /**
     * The second, non-hidden {@code Ability} a {@code Pokémon} may have.  May be the same as {@link #Slot0} for some
     * given {@code Pokémon}; but a different game index is still used.
     */
    Slot1,

    /**
     * The hidden {@code Ability} a {@code Pokémon} may have.  May be the same as {@link #Slot0} for some given {@code
     * Pokémon}; but a different game index is still used.
     */
    Hidden;

    /**
     * {@inheritDoc}
     */
    @Override
    public int index() {

        return this.ordinal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        switch (this) {
            case Slot0:
                return "0";
            case Slot1:
                return "1";
            case Hidden:
                return "H";

            default:
                throw new IllegalStateException();
        }
    }
}
