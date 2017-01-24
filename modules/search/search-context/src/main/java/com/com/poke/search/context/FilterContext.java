package com.com.poke.search.context;

import com.com.poke.model.*;

/**
 * Represents the assortment of {@link SearchFilter SearchFilters} which should be used when performing a search.
 */
public interface FilterContext {

    /**
     * The {@link SearchFilter} for the {@link AbilitySlot} of the context.
     *
     * @return The {@code SearchFilter} for the {@code AbilitySlot} of the context.
     */
    SearchFilter<AbilitySlot> abilitySlotFilter();

    /**
     * The {@link SearchFilter} for the {@link Ball} of the context.
     *
     * @return The {@code SearchFilter} for the {@code Ball} of the context.
     */
    SearchFilter<Ball> ballFilter();

    /**
     * The {@link SearchFilter} for the {@link Gender} of the context.
     *
     * @return The {@code SearchFilter} for the {@code Gender} of the context.
     */
    SearchFilter<Gender> genderFilter();

    /**
     * The {@link SearchFilter} for the {@link IVs} of the context.
     *
     * @return The {@code SearchFilter} for the {@code IVs} of the context.
     */
    SearchFilter<IVs> ivsFilter();

    /**
     * The {@link SearchFilter} for the {@link Nature} of the context.
     *
     * @return The {@code SearchFilter} for the {@code Nature} of the context.
     */
    SearchFilter<Nature> natureFilter();

    /**
     * The {@link SearchFilter} for the {@code shinyValue} of the context.
     *
     * @return The {@code SearchFilter} for the {@code Ball} of the context.
     */
    SearchFilter<Integer> shinyValueFilter();

    /**
     * The {@link SearchFilter} for the {@link Type hidden power Type} of the context.
     *
     * @return The {@code SearchFilter} for the {@code hidden power Type} of the context.
     */
    SearchFilter<Type> hiddenPowerTypeFilter();
}
