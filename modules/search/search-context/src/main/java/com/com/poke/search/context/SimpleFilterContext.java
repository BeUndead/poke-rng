package com.com.poke.search.context;

import com.com.poke.model.*;

public class SimpleFilterContext implements FilterContext {

    private final SearchFilter<AbilitySlot> abilitySlotFilter;

    private final SearchFilter<Ball> ballFilter;

    private final SearchFilter<Gender> genderFilter;

    private final SearchFilter<IVs> ivsFilter;

    private final SearchFilter<Nature> natureFilter;

    private final SearchFilter<Integer> shinyValueFilter;

    private final SearchFilter<Type> hiddenPowerTypeFilter;

    SimpleFilterContext(
            final SearchFilter<AbilitySlot> abilitySlotFilter,
            final SearchFilter<Ball> ballFilter,
            final SearchFilter<Gender> genderFilter,
            final SearchFilter<IVs> ivsFilter,
            final SearchFilter<Nature> natureFilter,
            final SearchFilter<Integer> shinyValueFilter,
            final SearchFilter<Type> hiddenPowerTypeFilter) {

        this.abilitySlotFilter = abilitySlotFilter;
        this.ballFilter = ballFilter;
        this.genderFilter = genderFilter;
        this.ivsFilter = ivsFilter;
        this.natureFilter = natureFilter;
        this.shinyValueFilter = shinyValueFilter;
        this.hiddenPowerTypeFilter = hiddenPowerTypeFilter;
    }

    public static SimpleFilterContext.Builder builder() {

        return new SimpleFilterContext.Builder();
    }

    @Override
    public SearchFilter<AbilitySlot> abilitySlotFilter() {

        return this.abilitySlotFilter;
    }

    @Override
    public SearchFilter<Ball> ballFilter() {

        return this.ballFilter;
    }

    @Override
    public SearchFilter<Gender> genderFilter() {

        return this.genderFilter;
    }

    @Override
    public SearchFilter<IVs> ivsFilter() {

        return this.ivsFilter;
    }

    @Override
    public SearchFilter<Nature> natureFilter() {

        return this.natureFilter;
    }

    @Override
    public SearchFilter<Integer> shinyValueFilter() {

        return this.shinyValueFilter;
    }

    @Override
    public SearchFilter<Type> hiddenPowerTypeFilter() {

        return this.hiddenPowerTypeFilter;
    }

    /**
     * Simple {@code Builder} class for {@link SimpleFilterContext}.
     */
    public static final class Builder {

        private SearchFilter<AbilitySlot> abilitySlotFilter;

        private SearchFilter<Ball> ballFilter;

        private SearchFilter<Gender> genderFilter;

        private SearchFilter<IVs> ivsFilter;

        private SearchFilter<Nature> natureFilter;

        private SearchFilter<Integer> shinyValueFilter;

        private SearchFilter<Type> hiddenPowerTypeFilter;

        private Builder() {

            this.abilitySlotFilter = ability -> true;
            this.ballFilter = ball -> true;
            this.genderFilter = gender -> true;
            this.ivsFilter = ivs -> true;
            this.natureFilter = nature -> true;
            this.shinyValueFilter = shinyValue -> true;
            this.hiddenPowerTypeFilter = hiddenPowerType -> true;
        }

        /**
         * Generates a {@link SearchFilter} from the givne {@code filter}.
         *
         * @param filter The {@code fiter} to use.
         * @param <T>    The {@code Type} of the {@code SearchFilter} to use.
         * @return The given {@code filter} if it is non-{@code null}; otherwise an all {@link
         * SearchFilter#isAcceptable(Object) accepting} {@code SearchFilter} for the specified {@code Type}, {@code T}.
         */
        private static <T> SearchFilter<T> buildFilter(final SearchFilter<T> filter) {

            if (filter == null) {
                return item -> true;
            } else {
                return filter;
            }
        }

        /**
         * Generates the {@link SimpleFilterContext} with the latest values from this {@link Builder}.
         *
         * @return A {@link SimpleFilterContext} with the values provided to this {@code Builder}; or else defaults.
         */
        public SimpleFilterContext build() {

            return new SimpleFilterContext(
                    this.abilitySlotFilter,
                    this.ballFilter,
                    this.genderFilter,
                    this.ivsFilter,
                    this.natureFilter,
                    this.shinyValueFilter,
                    this.hiddenPowerTypeFilter
            );
        }

        /**
         * Appends the provided {@link AbilitySlot ability slot filter} to this {@link Builder}.
         *
         * @param abilitySlotFilter The {@code rng} to append.
         * @return The current {@code FilterContext Builder} with the {@code abilitySlotFilter} replaced by the given
         * {@code ability slot filter}.
         */
        public SimpleFilterContext.Builder withAbilitySlotFiter(final SearchFilter<AbilitySlot> abilitySlotFilter) {

            this.abilitySlotFilter = SimpleFilterContext.Builder.buildFilter(abilitySlotFilter);
            return this;
        }

        /**
         * Appends the provided {@link Ball ball filter} to this {@link Builder}.
         *
         * @param ballFilter The {@code Ball filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code ball filter} replaced by the given {@code
         * ball filter}.
         */
        public SimpleFilterContext.Builder withBallFilter(final SearchFilter<Ball> ballFilter) {

            this.ballFilter = SimpleFilterContext.Builder.buildFilter(ballFilter);
            return this;
        }

        /**
         * Appends the provided {@link Gender gender filter} to this {@link Builder}.
         *
         * @param genderFilter The {@code Gender filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code gender filter} replaced by the given {@code
         * gender filter}.
         */
        public SimpleFilterContext.Builder withGenderFilter(final SearchFilter<Gender> genderFilter) {

            this.genderFilter = SimpleFilterContext.Builder.buildFilter(genderFilter);
            return this;
        }

        /**
         * Appends the provided {@link IVs filter} to this {@link Builder}.
         *
         * @param ivsFilter The {@code IVs filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code IVs filter} replaced by the given {@code
         * IVs filter}.
         */
        public SimpleFilterContext.Builder withIVsFilter(final SearchFilter<IVs> ivsFilter) {

            this.ivsFilter = SimpleFilterContext.Builder.buildFilter(ivsFilter);
            return this;
        }

        /**
         * Appends the provided {@link Nature filter} to this {@link Builder}.
         *
         * @param natureFilter The {@code Nature filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code Nature filter} replaced by the given {@code
         * Nature filter}.
         */
        public SimpleFilterContext.Builder withNatureFilter(final SearchFilter<Nature> natureFilter) {

            this.natureFilter = SimpleFilterContext.Builder.buildFilter(natureFilter);
            return this;
        }

        /**
         * Appends the provided {@code shiny value filter} to this {@link Builder}.
         *
         * @param shinyValueFilter The {@code shiny value filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code shiny value filter} replaced by the given
         * {@code shiny value filter}.
         */
        public SimpleFilterContext.Builder withShinyValueFilter(final SearchFilter<Integer> shinyValueFilter) {

            this.shinyValueFilter = SimpleFilterContext.Builder.buildFilter(shinyValueFilter);
            return this;
        }

        /**
         * Appends the provided {@link Type hidden power type filter} to this {@link Builder}.
         *
         * @param hiddenPowerTypeFilter The {@code hidden power type filter} to append.
         * @return The current {@code FilterContext Builder} with the {@code hidden power type filter} replaced by the
         * given {@code hidden power type filter}.
         */
        public SimpleFilterContext.Builder withHiddenPowerTypeFilter(final SearchFilter<Type> hiddenPowerTypeFilter) {

            this.hiddenPowerTypeFilter = SimpleFilterContext.Builder.buildFilter(hiddenPowerTypeFilter);
            return this;
        }
    }
}
