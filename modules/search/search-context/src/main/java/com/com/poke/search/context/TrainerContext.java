package com.com.poke.search.context;

/**
 * Context for the trainer performing a given breed.
 */
public interface TrainerContext {

    /**
     * The {@code shiny value} of the trainer.
     *
     * @return The {@code shiny value} of the trainer.
     */
    int shinyValue();

    /**
     * Whether or not the trainer has the {@code Shiny Charm}.
     *
     * @return {@code true} if the trainer does have the {@code Shiny Charm}; otherwise {@code false}.
     */
    boolean hasShinyCharm();
}
