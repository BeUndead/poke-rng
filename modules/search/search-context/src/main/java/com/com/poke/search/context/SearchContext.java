package com.com.poke.search.context;

import com.com.rng.mt.MTRng;

/**
 * Context for a specific context.
 */
public interface SearchContext {

    /**
     * The {@link MTRng} (in the appropriate {@code state}) to use in the context.
     *
     * @return The {@code rng} (in the appropriate {@code state}) the use in the context.
     */
    MTRng rng();

    /**
     * The initial frame (advances to {@link #rng()} before computation of results) to use in the context.
     *
     * @return The initial frame to use in the context.
     */
    int initialFrame();

    /**
     * The number of frames (relative to the {@link #initialFrame() initial frame}) to check results for in this
     * context.
     *
     * @return The number of frames to check results for in this context.
     */
    int numberOfFrames();
}
