package com.com.poke.ui.components.widgets;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public final class PairedIVSpinner extends IVSpinner {

    private final PairMembershipType membershipType;

    public PairedIVSpinner(final PairMembershipType membershipType, final int initialValue) {

        super(initialValue);
        this.membershipType = membershipType;
    }

    public PairedIVSpinner(final PairMembershipType membershipType) {

        super();
        this.membershipType = membershipType;
    }

    public void setPartner(final PairedIVSpinner partner) {

        if (partner.membershipType == this.membershipType) {
            throw new IllegalArgumentException("Cannot 'setPartner' for partner of same MembershipType");
        }
        this.configurePartnership(partner);
    }

    private void configurePartnership(final PairedIVSpinner partner) {

        this.addChangeListener(changeEvent -> {
            final BooleanSupplier test;
            switch (this.membershipType) {
                case Minimum:
                    test = () -> partner.getIV() < this.getIV();
                    break;
                case Maximum:
                    test = () -> this.getIV() < partner.getIV();
                    break;
                default:
                    throw new IllegalStateException(
                            "Unknown value for 'membershipType': " + Objects.toString(membershipType));
            }

            if (test.getAsBoolean()) {
                partner.setIV(this.getIV());
            }
        });
    }

    public enum PairMembershipType {
        Minimum,
        Maximum
    }
}
