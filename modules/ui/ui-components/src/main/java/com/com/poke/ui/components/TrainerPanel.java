package com.com.poke.ui.components;

import com.com.poke.search.context.SimpleTrainerContext;
import com.com.poke.search.context.TrainerContext;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class TrainerPanel extends JPanel implements Supplier<TrainerContext> {

    private final JLabel tidLabel = new JLabel();
    private final JLabel sidLabel = new JLabel();
    private final JLabel tsvLabel = new JLabel();
    private final JSpinner tidSpinner = new JSpinner();
    private final JSpinner sidSpinner = new JSpinner();
    private final JSpinner tsvSpinner = new JSpinner();
    private final JCheckBox shinyCharmCheckBox = new JCheckBox();

    TrainerPanel() {

        initComponents();
    }

    private static int getValue(final JSpinner spinner) {

        return ((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
    }

    @Override
    public TrainerContext get() {

        return new SimpleTrainerContext(getValue(tsvSpinner), shinyCharmCheckBox.isSelected());
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.trainer")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3",
                "[60!][120!]",
                "[30!][30!][30!][30!]"));

        //---- tidLabel ----
        tidLabel.setText(bundle.getString("trainer.tid"));
        tidLabel.setLabelFor(tidSpinner);
        add(tidLabel, "cell 0 0,alignx right,growx 0");

        //---- tidSpinner ----
        tidSpinner.setModel(new SpinnerNumberModel(0, 0, 65535, 1));
        tidSpinner.setValue(20759);
        add(tidSpinner, "cell 1 0,growx");

        //---- sidLabel ----
        sidLabel.setText(bundle.getString("trainer.sid"));
        sidLabel.setLabelFor(sidSpinner);
        add(sidLabel, "cell 0 1,alignx right,growx 0");

        //---- sidSpinner ----
        sidSpinner.setModel(new SpinnerNumberModel(0, 0, 65535, 1));
        sidSpinner.setValue(21518);
        add(sidSpinner, "cell 1 1,growx");

        //---- label3 ----
        tsvLabel.setText(bundle.getString("trainer.tsv"));
        tsvLabel.setLabelFor(tsvSpinner);
        add(tsvLabel, "cell 0 2,alignx right,growx 0");

        //---- tsvSpinner ----
        tsvSpinner.setModel(new SpinnerNumberModel(0, 0, 4095, 1));
        tsvSpinner.setValue(81);
        add(tsvSpinner, "cell 1 2,growx");

        //---- shinyCharmCheckBox ----
        shinyCharmCheckBox.setSelected(true);
        shinyCharmCheckBox.setText(bundle.getString("item.shinyCharm"));
        shinyCharmCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        add(shinyCharmCheckBox, "cell 0 3 2 1,alignx right,growx 0");

        configureListeners();
    }

    private void configureListeners() {

        final ChangeListener updateTsvSpinner = changeEvent -> {
            final int tid = getValue(tidSpinner);
            final int sid = getValue(sidSpinner);

            int tsv = (tid ^ sid) >>> 4;
            tsvSpinner.setValue(tsv);
        };

        tidLabel.setEnabled(false);
        sidLabel.setEnabled(false);
        tidSpinner.setEnabled(false);
        sidSpinner.setEnabled(false);

        final MouseListener enableOnClick = new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent mouseEvent) {

                final boolean onTsv = onTsvComponent(mouseEvent);
                tidLabel.setEnabled(!onTsv);
                sidLabel.setEnabled(!onTsv);
                tidSpinner.setEnabled(!onTsv);
                sidSpinner.setEnabled(!onTsv);

                tsvLabel.setEnabled(onTsv);
                tsvSpinner.setEnabled(onTsv);

                if (!onTsv) {
                    // Call the updateTsvSpinner ChangeListener manually.
                    updateTsvSpinner.stateChanged(null);
                }
            }

            private boolean onTsvComponent(final MouseEvent mouseEvent) {

                return mouseEvent.getComponent().equals(tsvLabel)
                        || mouseEvent.getComponent().equals(tsvSpinner);
            }
        };

        tidLabel.addMouseListener(enableOnClick);
        sidLabel.addMouseListener(enableOnClick);
        tidSpinner.addMouseListener(enableOnClick);
        sidSpinner.addMouseListener(enableOnClick);
        tsvLabel.addMouseListener(enableOnClick);
        tsvSpinner.addMouseListener(enableOnClick);

        tidSpinner.addChangeListener(updateTsvSpinner);
        sidSpinner.addChangeListener(updateTsvSpinner);
    }
}
