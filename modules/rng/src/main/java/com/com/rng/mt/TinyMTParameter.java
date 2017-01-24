package com.com.rng.mt;

/**
 * Represents the various parameters which are provided to the construction of a {@link TinyMT}.
 */
final class TinyMTParameter {

    private static final TinyMTParameter POKEMON_TINYMT_PARAMETER
            = new TinyMTParameter(0x3793_fdff, 0x8f70_11ee, 0xfc78_ff1f);

    private final int temperingMatrix;

    private final int xoringMatrix1;

    private final int xoringMatrix2;

    private TinyMTParameter(
            final int temperingMatrix,
            final int xoringMatrix1,
            final int xoringMatrix2) {

        this.temperingMatrix = temperingMatrix;
        this.xoringMatrix1 = xoringMatrix1;
        this.xoringMatrix2 = xoringMatrix2;
    }

    static TinyMTParameter getPokemonDefault() {

        return TinyMTParameter.POKEMON_TINYMT_PARAMETER;
    }

    /**
     * The matrix used in tempering results.
     *
     * @return The matrix used in tempering results.
     */
    int getTemperingMatrix() {

        return this.temperingMatrix;
    }

    /**
     * The matrix used in the first {@code xor} computation during calculation of the next value of the {@link TinyMT}.
     *
     * @return The matrix used in the first {@code xor} computation during calculation of the next value.
     */
    int getXoringMatrix1() {

        return this.xoringMatrix1;
    }

    /**
     * The matrix used in the second {@code xor} computation during calculation of the next value of the {@link TinyMT}.
     *
     * @return The matrix used in the second {@code xor} computation during calculation of the next value.
     */
    int getXoringMatrix2() {

        return this.xoringMatrix2;
    }
}
