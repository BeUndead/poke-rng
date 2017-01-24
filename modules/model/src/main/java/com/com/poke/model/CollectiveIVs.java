package com.com.poke.model;

/**
 * Represents the exact {@link IVs} of a {@code Pokémon}.
 */
public final class CollectiveIVs implements IVs {

    /**
     * The actual {@link IVs}, stored as a {@code byte} {@code array}; in order:
     * <ol>
     * <li>{@link Stats#HP};</li>
     * <li>{@link Stats#Attack};</li>
     * <li>{@link Stats#Defense};</li>
     * <li>{@link Stats#SpecialAttack};</li>
     * <li>{@link Stats#SpecialDefense};</li>
     * <li>{@link Stats#Speed}.</li>
     * </ol>
     */
    private final byte[] ivs;

    /**
     * The {@link Type} of the {@code hidden power} specified by these {@link IVs}.
     */
    private final Type hiddenPower;

    /**
     * Constructor; package local.  Use {@link #from(byte[])} and {@link #from(int[])} instead.
     *
     * @param ivs The {@code byte} {@code array} representing the {@link IVs}; in order matching those for {@link
     *            #ivs}.
     * @throws IllegalArgumentException If the provided {@code ivs} do not match the assumptions above.
     * @see #validate()
     */
    private CollectiveIVs(final byte[] ivs) {

        this.ivs = ivs;
        this.hiddenPower = this.initialiseHiddenPower();
        validate();
    }

    /**
     * Stat factory constructor; generates an {@link IVs} implementation with the specified {@code ivs}.
     *
     * @param ivs The {@code ivs}, as an {@code array} of {@code ints}.
     * @return An {@code IVs} implementation with the provided {@code ivs}.
     * @throws IllegalArgumentException If any of the {@code IVs} assumptions listed in {@link #CollectiveIVs(byte[])}'s
     *                                  javadoc do not hold.
     * @see #validate()
     */
    public static IVs from(final int[] ivs) {

        final byte[] ivsAsByteArray = new byte[6];
        for (int i = 0; i < ivs.length; i++) {
            ivsAsByteArray[i] = (byte) ivs[i];
        }
        return new CollectiveIVs(ivsAsByteArray);
    }

    /**
     * Stat factory constructor; generates an {@link IVs} implementation with the specified {@code ivs}.
     *
     * @param ivs The {@code ivs}, as an {@code array} of {@code bytes}.
     * @return An {@code IVs} implementation with the provided {@code ivs}.
     * @throws IllegalArgumentException If any of the {@code IVs} assumptions do not hold.
     * @see #validate()
     */
    public static IVs from(final byte[] ivs) {

        final byte[] ivsAsByteArray = new byte[6];
        System.arraycopy(ivs, 0, ivsAsByteArray, 0, ivs.length);
        return new CollectiveIVs(ivsAsByteArray);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IV forStat(final Stat stat) {

        return IndividualIV.of(this.ivs[stat.index()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type hiddenPower() {

        return this.hiddenPower;
    }

    /**
     * Internal method for inititlising the {@link #hiddenPower} {@link Type}.
     *
     * @return The {@link Type} of the {@code hidden power} provided by these {@link IVs}.
     */
    private Type initialiseHiddenPower() {

        final class HiddenPowerComputationHelper {

            private int parity(final IV iv) {

                return iv.value() & 0b1;
            }
        }

        final HiddenPowerComputationHelper helper = new HiddenPowerComputationHelper();

        int temp = 0;

        temp += helper.parity(this.forStat(Stats.HP));
        temp += 2 * helper.parity(this.forStat(Stats.Attack));
        temp += 4 * helper.parity(this.forStat(Stats.Defense));
        temp += 8 * helper.parity(this.forStat(Stats.Speed));
        temp += 16 * helper.parity(this.forStat(Stats.SpecialAttack));
        temp += 32 * helper.parity(this.forStat(Stats.SpecialDefense));
        temp *= 15;

        final int hiddenPowerTypeIndex = temp / 63;
        return Types.values()[hiddenPowerTypeIndex + 1];
    }

    /**
     * Validates that the current {@link #ivs} meet the requirements for being a valid {@link IVs} set.  These are:
     * <ul>
     * <li>Have length {@code 6};</li>
     * <li>Each is between {@code 0} and {@code 31} inclusively.</li>
     * </ul>
     *
     * @throws IllegalArgumentException If either of the above assumptions does not hold for the current {@code ivs}.
     */
    private void validate() {

        if (this.ivs.length != 6) {
            throw new IllegalArgumentException("Expected 6 CollectiveIVs");
        }
        for (final byte iv : ivs) {
            if (iv < (byte) 0 || iv > (byte) 31) {
                throw new IllegalArgumentException("IV out of expected range");
            }
        }
    }
}
