package com.com.poke.model;

/**
 * Represents one of the slots for an {@code Ability} which a {@code Pokémon} can have.
 */
public interface AbilitySlot {

    /**
     * The in-game index of the {@link AbilitySlot} in question.
     *
     * @return The in-game index of the {@link AbilitySlot} in question.
     */
    int index();
}
