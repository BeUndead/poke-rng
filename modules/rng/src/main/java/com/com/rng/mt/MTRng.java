package com.com.rng.mt;

/**
 * Represents a simple {@code Mersenne Twister} {@code rng}.
 */
public interface MTRng {

    /**
     * The next {@code int} for this {@link MTRng}.
     *
     * @return The next {@code int} for this {@code MTRng}.
     */
    int nextInt();

    /**
     * The next {@code int} for this {@link MTRng}, taken modulo {@code modulus}.
     *
     * @param modulus The {@code modulus} of the {@code int} to return.
     * @return The next {@code int} for this {@code MTRng}, taken modulo {@code modulus}.
     */
    int nextInt(int modulus);

    /**
     * The current {@code int} for this {@link MTRng}.
     *
     * @return The current {@code int} for this {@code MTRng}.
     */
    int currentInt();

    /**
     * The current {@code int} for this {@link MTRng}, taken modulo {@code modulus}.
     *
     * @param modulus The {@code modulus} of the {@code int} to return.
     * @return The current {@code int} for this {@code MTRng}, taken modulo {@code modulus}.
     */
    int currentInt(int modulus);

    /**
     * The number of advancements performed by this {@link MTRng} since generation.
     *
     * @return The number of advancements performed by this {@code MTRng} since generation.
     */
    long advancements();

    /**
     * The current internal array's state for this {@link MTRng}.
     *
     * @return The current internal array's state for this {@code MTRng}.
     */
    int[] state();

    /**
     * Generates a copy of this {@link MTRng}, but with {@link #advancements()} reset.
     *
     * @return A new {@link MTRng} with the same state as this one, but with {@code advancements} reset.
     */
    MTRng cloneAndResetAdvancements();
}
