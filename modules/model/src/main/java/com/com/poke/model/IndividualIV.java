package com.com.poke.model;

/**
 * Simple implementation of {@link IV} to represent an individual {@code IV}.
 */
public class IndividualIV implements IV {

    private final byte value;

    private IndividualIV(final byte value) {

        this.value = value;
    }

    /**
     * Static factory for producing an {@link IV} of the specified {@code value}.
     *
     * @param value The {@code value} of the {@link IV} to generate (as a {@code byte}).
     * @return An {@code IV} with the specified {@code value}.
     * @throws IllegalArgumentException If the given {@code value} is not valid for an {@code IV}.
     */
    public static IV of(final byte value) {

        if (value < 0 || value > 31) {
            throw new IllegalArgumentException("'value' must be ≥ 0 and ≤ 31");
        }
        return new IndividualIV(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte value() {

        return this.value;
    }
}
