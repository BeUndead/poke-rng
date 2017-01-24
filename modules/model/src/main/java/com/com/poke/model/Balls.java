package com.com.poke.model;

/**
 * Convenience {@link Enum} specifying the various {@code Poké Balls} which {@code Pokémon} may be in.
 */
public enum Balls implements Ball {

    /**
     * The default {@link Ball} when inheritance cannot be achieved.
     */
    Poke,

    /**
     * An improved (when compared to the {@link #Poke Poke Ball}) {@link Ball}.
     */
    Great,

    /**
     * An improved (when compared to the {@link #Great Great Ball}) {@link Ball}.
     */
    Ultra,

    /**
     * A {@link Ball} which <strong>cannot fail</strong> to catch a {@code Pokémon} (provided that the {@code Pokémon}
     * can be caught).  This {@code Ball} cannot be inherited via breeding.
     */
    Master {
        @Override
        public boolean canBeInherited() {

            return false;
        }
    },

    /**
     * A {@link Ball} used specially in certain safari zones throughout the {@code Pokémon} universe.
     */
    Safari,

    /**
     * A {@link Ball} specially adapted for capturing {@link Types#Water} and {@link Types#Bug} {@link Type}
     * {@code Pokémon}.
     */
    Net,

    /**
     * A {@link Ball} specially adapted for capturing {@code Pokémon} whilst underwater.
     */
    Dive,

    /**
     * A {@link Ball} specially adapted for capturing low level {@code Pokémon}.
     */
    Nest,

    /**
     * A {@link Ball} specially adapted for capturing {@code Pokémon} with a species which the thrower has already
     * captured.
     */
    Repeat,

    /**
     * A {@link Ball} specially adapted for capturing {@code Pokémon} after several turns of battle.
     */
    Timer,

    /**
     * A {@link Ball} which is especially comfy for {@code Pokémon}.  Provides no advantage for capture; but increases
     * the rate at which the contained {@code Pokémon's} happiness increases.
     */
    Luxury,

    /**
     * A {@link Ball} with no special adaptions.
     */
    Premier,

    /**
     * A {@link Ball} specially adapted for capturing {@code Pokémon} in the dark (at night or in caves).
     */
    Dusk,

    /**
     * A {@link Ball} which will restore the captured {@code Pokémon's} health upon successful capture.
     */
    Heal,

    /**
     * A {@link Ball} specially adapted for capturing {@code Pokémon} during the first few turns of battle.
     */
    Quick,

    /**
     * A {@link Ball} used to contain certain gift {@code Pokémon}.  This {@code Ball} cannot be inherited through
     * breeding.
     */
    Cherish {
        @Override
        public boolean canBeInherited() {

            return false;
        }
    },

    /**
     * A {@link Ball} specially designed for capturing {@code Pokémon} with a high base {@link Stats#Speed}.
     */
    Fast,

    /**
     * A {@link Ball} specially designed for capturing {@code Pokémon} which are of a lower level than the {@code
     * Pokémon} in the battle (as the opponent to the capture candidate).
     */
    Level,

    /**
     * A {@link Ball} specially designed for capturing {@code Pokémon} whilst fishing.
     */
    Lure,

    /**
     * A {@link Ball} specially designed for capturing heavier {@code Pokémon}.
     */
    Heavy,

    /**
     * A {@link Ball} specially designed for capturing {@code Pokémon} with a {@link Gender} which differs from the
     * {@code Pokémon} in the battle (as the opponent to the capture candidate).
     */
    Love,

    /**
     * A {@link Ball} which increases the {@code Pokémon's} initial happiness upon capture.
     */
    Friend,

    /**
     * A {@link Ball} specially designed for capturing {@code Pokémon} which evolve through the use of a {@code
     * moon stone}.
     */
    Moon,

    /**
     * A {@link Ball} used specially in the {@code Johto} bug catching contest.
     */
    Sport,

    //    /**
    //     * A {@link Ball} used specially for capturing {@code Pokémon} during transfer between generations.
    //     */
    //    Park, // Not retained after capture.

    /**
     * A {@link Ball} used specially for capturing {@code Pokémon} from the {@code Dream Radar} or {@code Dream World}.
     */
    Dream,

    /**
     * A {@link Ball} specially designed for capturing ultra-beast {@code Pokémon}.  Has a very low capture rate for
     * any other {@code Pokémon}.
     */
    Beast
}
