package com.com.poke.search.compute;

import com.com.poke.search.context.FilterContext;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.SearchContext;
import com.com.poke.search.context.TrainerContext;

import java.util.Collection;

/**
 * Computes the {@link SearchResult SearchResults} for an individual context.
 */
public interface FilteredOffspringSpreadCalculator {

    /**
     * The {@link Collection} of {@link SearchResult SearchResults} (may be empty) which the given {@link
     * ParentsContext parents} and {@link TrainerContext trainer} details would provide, which match the {@link
     * com.com.poke.search.context.SearchFilter filters} described in the given {@link SearchContext}.
     *
     * @param parentsContext The details of the parents for the context.
     * @param trainerContext The details of the trainer for the context.
     * @param searchContext  The details of the search context (includes {@code rng} state).
     * @param filterContext  The details of the result filtering context.
     * @return A {@code Collection} of {@code SearchResults} which would be produced from the given {@code contexts},
     * and match the given {@code SearchContext}.
     */
    Collection<SearchResult> performSearch(
            ParentsContext parentsContext,
            TrainerContext trainerContext,
            SearchContext searchContext,
            FilterContext filterContext);
}
