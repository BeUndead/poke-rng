package com.com.poke.ui.components;

import com.com.poke.ui.components.widgets.HexFormatterFactory;
import com.com.rng.mt.MTRng;
import com.com.rng.mt.TinyMT;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class SeedPanel extends JPanel implements Supplier<MTRng> {

    private final JSpinner seed0Spinner = new JSpinner();
    private final JSpinner seed1Spinner = new JSpinner();
    private final JSpinner seed2Spinner = new JSpinner();
    private final JSpinner seed3Spinner = new JSpinner();

    SeedPanel() {

        initComponents();
    }

    @Override
    public MTRng get() {

        final int[] seed = new int[] {
                getValue(seed0Spinner),
                getValue(seed1Spinner),
                getValue(seed2Spinner),
                getValue(seed3Spinner)
        };
        return TinyMT.fromStatus(seed);
    }



    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");
        final JLabel seed0Label = new JLabel();
        final JLabel seed1Label = new JLabel();
        final JLabel seed2Label = new JLabel();
        final JLabel seed3Label = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.seed")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3",
                "[20!][180:180:180]",
                "[30!][30!][30!][30!]"));

        //---- seed0Label ----
        seed0Label.setText("0");
        add(seed0Label, "cell 0 0,alignx right,growx 0");

        //---- seed0Spinner ----
        seed0Spinner.setModel(
                new SpinnerNumberModel(new Long(0xdf33_b0ddL), new Long(0L), new Long(0xffff_ffffL), new Long(1L)));
        add(seed0Spinner, "cell 1 0,growx");

        //---- seed1Label ----
        seed1Label.setText("1");
        add(seed1Label, "cell 0 1,alignx right,growx 0");

        //---- seed1Spinner ----
        seed1Spinner.setModel(
                new SpinnerNumberModel(new Long(0x7483_a73aL), new Long(0L), new Long(0xffff_ffffL), new Long(1L)));
        add(seed1Spinner, "cell 1 1,growx");

        //---- seed2Label ----
        seed2Label.setText("2");
        add(seed2Label, "cell 0 2,alignx right,growx 0");

        //---- seed2Spinner ----
        seed2Spinner.setModel(
                new SpinnerNumberModel(new Long(0x2da1_62caL), new Long(0L), new Long(0xffff_ffffL), new Long(1L)));
        add(seed2Spinner, "cell 1 2,growx");

        //---- seed3Label ----
        seed3Label.setText("3");
        add(seed3Label, "cell 0 3,alignx right,growx 0");

        //---- seed3Spinner ----
        seed3Spinner.setModel(
                new SpinnerNumberModel(new Long(0x7c40_3098L), new Long(0L), new Long(0xffff_ffffL), new Long(1L)));
        add(seed3Spinner, "cell 1 3,growx");

        configureHexFormatting(seed0Spinner);
        configureHexFormatting(seed1Spinner);
        configureHexFormatting(seed2Spinner);
        configureHexFormatting(seed3Spinner);
    }

    private void configureHexFormatting(final JSpinner spinner) {

        final JFormattedTextField formattedTextField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        formattedTextField.setFormatterFactory(new HexFormatterFactory());
    }

    private int getValue(final JSpinner spinner) {

        final long value = ((SpinnerNumberModel) spinner.getModel()).getNumber().longValue();
        return (int) value;
    }
}
