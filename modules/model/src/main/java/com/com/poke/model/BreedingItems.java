package com.com.poke.model;

/**
 * Convenience {@code Enum} of the various {@link BreedingItem BreedingItems} which a {@code Pokémon} may hold to
 * influence the offspring of a breed.
 */
public enum BreedingItems implements BreedingItem {

    /**
     * No Item.  Has no effect on the offspring.
     */
    None,

    /**
     * Causes 5 {@link IV IVs} to be inherited from the parents; rather than the default, 3.
     */
    DestinyKnot,

    /**
     * If held by only one parent, guarantees that the offspring will inherit the {@link Nature} of that parent.  If
     * held by <strong>both</strong> parents, each of their {@code Natures} has a 50/50 chance of being inherited.
     */
    Everstone,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#HP} {@link Stat} from that
     * parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code HP} stat being inherited
     * from each.
     */
    PowerWeight,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#Attack} {@link Stat} from that
     * parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code Attack} stat being
     * inherited from each.
     */
    PowerBracer,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#Defense} {@link Stat} from
     * that parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code Defense} stat being
     * inherited from each.
     */
    PowerBelt,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#SpecialAttack} {@link Stat}
     * from that parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code SpecialAttack}
     * stat being inherited from each.
     */
    PowerLens,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#SpecialDefense} {@link Stat}
     * from that parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code SpecialAttack}
     * stat being inherited from each.
     */
    PowerBand,

    /**
     * If held by one parent, guarantees that the offspring will inherit the {@link Stats#Speed} {@link Stat} from that
     * parent.  If held by <strong>both</strong> parents, there is a 50/50 chance of the {@code Speed} stat being
     * inherited from each.
     */
    PowerAnklet,

    /**
     * Used to indicate that <strong>some</strong> {@code Item} is being held by the parent; but that it is
     * <strong>not</strong> one of the ones explicitly listed above.  Has no effect on the offspring.
     */
    Other
}
