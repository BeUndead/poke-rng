package com.com.poke.ui.components.widgets;

import javax.swing.*;

public class IVSpinner extends JSpinner {

    IVSpinner(final int initialValue) {

        super(new SpinnerNumberModel(initialValue, 0, 31, 1));
    }

    public IVSpinner() {

        this(31);
    }

    @Override
    public final SpinnerNumberModel getModel() {

        return (SpinnerNumberModel) super.getModel();
    }

    public final byte getIV() {

        return this.getModel().getNumber().byteValue();
    }

    public final void setIV(final byte value) {

        if (value < 0 || value > 31) {
            throw new IllegalArgumentException("'value' must be ≥ 0 and ≤ 31");
        }
        this.getModel().setValue(value);
    }
}
