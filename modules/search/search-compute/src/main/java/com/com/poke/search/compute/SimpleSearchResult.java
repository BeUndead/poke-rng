package com.com.poke.search.compute;

/**
 * Implementation of {@link SearchResult}.
 */
class SimpleSearchResult implements SearchResult {

    private final OffspringSpread offspringSpread;

    private final long advancementsFromInitial;

    private final long numberOfAdvancements;

    SimpleSearchResult(
            final OffspringSpread offspringSpread,
            final long advancementsFromInitial,
            final long numberOfAdvancements) {

        this.offspringSpread = offspringSpread;
        this.advancementsFromInitial = advancementsFromInitial;
        this.numberOfAdvancements = numberOfAdvancements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffspringSpread offspringSpread() {

        return this.offspringSpread;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long frameFromInitial() {

        return this.advancementsFromInitial;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long rngAdvancements() {

        return this.numberOfAdvancements;
    }
}
