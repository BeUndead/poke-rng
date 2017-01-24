package com.com.poke.search.context;

/**
 * Provides a filter for the results returned, based on the {@code Type} of {@code T}.
 *
 * @param <T> The {@code Type} to filter on.
 */
public interface SearchFilter<T> {

    /**
     * Whether or not to isAcceptable the given {@code T}, {@code item}, for this {@link SearchFilter}.
     *
     * @param item The item to determine the acceptance for.
     * @return {@code true} if the {@code T}, {@code item} is acceptable by this {@code SearchFilter}; otherwise {@code
     * false}.
     */
    boolean isAcceptable(final T item);
}
