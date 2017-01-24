package com.com.poke.search.context;


import com.com.rng.mt.MTRng;

/**
 * Implementation of {@link SearchContext}.
 */
public final class SimpleSearchContext implements SearchContext {

    private final MTRng rng;

    private final int initialFrame;

    private final int numberOfFrames;

    public SimpleSearchContext(final MTRng rng, final int initialFrame, final int numberOfFrames) {

        this.rng = rng;
        this.initialFrame = initialFrame;
        this.numberOfFrames = numberOfFrames;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MTRng rng() {

        return this.rng;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int initialFrame() {

        return this.initialFrame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfFrames() {

        return this.numberOfFrames;
    }

}
