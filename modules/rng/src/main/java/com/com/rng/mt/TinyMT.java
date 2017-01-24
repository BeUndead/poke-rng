package com.com.rng.mt;

/**
 * {@link MTRng} implementation utilising the {@code TinyMT} specifics.
 */
public final class TinyMT implements MTRng {

    private static final int MASK = 0x7fff_ffff;

    private static final int SH0 = 1;

    private static final int SH1 = 10;

    private static final int SH8 = 8;

    private final TinyMTParameter tinyMTParameter;

    private final int[] currentState = new int[4];

    private long advancements;

    private TinyMT(final TinyMTParameter tinyMTParameter, final int[] initialStatus) {

        this.tinyMTParameter = tinyMTParameter;
        System.arraycopy(initialStatus, 0, this.currentState, 0, initialStatus.length);
        this.advancements = 0L;
    }

    private TinyMT(final int[] initialStatus) {

        this(TinyMTParameter.getPokemonDefault(), initialStatus);
    }

    /**
     * Generates a {@link TinyMT} using the {@code Pokémon} default, from the given {@link String}.
     *
     * @param initialStatusString The initial status, represented as a {@code String}.
     * @return An {@link MTRng} with the specified {@code initialStatus}.
     */
    public static TinyMT fromString(final String initialStatusString) {

        final int[] initialStatus = new int[4];
        for (int i = 0; i < 4; i++) {
            final String subStatus = initialStatusString.substring(8 * i, 8 * (i + 1));
            initialStatus[3 - i] = Integer.parseUnsignedInt(subStatus, 16);
        }
        return new TinyMT(initialStatus);
    }

    public static TinyMT fromStatus(final int[] status) {

        return new TinyMT(status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int currentInt() {

        return this.temper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int currentInt(final int modulus) {

        return Integer.remainderUnsigned(this.currentInt(), modulus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextInt() {

        this.advanceState();
        return this.currentInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextInt(final int modulus) {

        this.advanceState();
        return this.currentInt(modulus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long advancements() {

        return this.advancements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MTRng cloneAndResetAdvancements() {

        return new TinyMT(this.currentState);
    }

    /**
     * Generates a {@link String} representation of the current state of this {@link MTRng}.
     *
     * @return A {@code String} representation of the current state of this {@code MTRng}.
     */
    public String internalState() {

        final StringBuilder builder = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            builder.append(intToHexString(this.currentState[i]));
            //            builder.append(Integer.toUnsignedString(this.currentState[i], 16));
        }
        return builder.toString();
    }

    /**
     * Advances the {@link #currentState} array to the next value.
     */
    private void advanceState() {

        int y = this.currentState[3];
        int x = (this.currentState[0] & MASK) ^ this.currentState[1] ^ this.currentState[2];
        x ^= (x << SH0);
        y ^= (y >>> SH0) ^ x;

        this.currentState[0] = this.currentState[1];
        this.currentState[1] = this.currentState[2];
        this.currentState[2] = x ^ (y << SH1);
        this.currentState[3] = y;
        if ((y & 1) == 1) {
            this.currentState[1] ^= this.tinyMTParameter.getXoringMatrix1();
            this.currentState[2] ^= this.tinyMTParameter.getXoringMatrix2();
        }
        this.advancements++;
    }

    /**
     * Checks the current value returned by this {@link MTRng rng}.
     *
     * @return The current {@code int} value for this {@code rng}.
     */
    private int temper() {

        int t0 = this.currentState[3];
        int t1 = this.currentState[0] + (this.currentState[2] >>> SH8);
        t0 ^= t1;
        if ((t1 & 1) == 1) {
            t0 ^= this.tinyMTParameter.getTemperingMatrix();
        }
        return t0;
    }

    private String intToHexString(final int uint) {

        return String.format("[%04x %04x]", ((uint & 0xffff_0000L) >>> 0x10), (uint & 0xffffL));
    }
}
