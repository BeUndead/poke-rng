package com.com.poke.ui.components;

import com.com.poke.search.context.SearchContext;
import com.com.poke.search.context.SimpleSearchContext;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class SearchPanel extends JPanel implements Supplier<SearchContext> {

    private final SeedPanel seedPanel = new SeedPanel();
    private final JSpinner startingFrameSpinner = new JSpinner();
    private final JSpinner searchFramesSpinner = new JSpinner();

    SearchPanel() {

        initComponents();
    }

    private static int getValue(final JSpinner spinner) {

        return ((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
    }

    @Override
    public SearchContext get() {

        return new SimpleSearchContext(seedPanel.get(), getValue(startingFrameSpinner), getValue(searchFramesSpinner));
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");
        final JLabel startingFrameLabel = new JLabel();
        final JLabel searchFramesLabel = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.search")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3",
                "[80:190:100][90:100:110]",
                "[fill][30!][30!]"));
        add(seedPanel, "cell 0 0 2 1,grow");

        //---- startingFrameLabel ----
        startingFrameLabel.setText(bundle.getString("search.initial"));
        startingFrameLabel.setLabelFor(startingFrameSpinner);
        add(startingFrameLabel, "cell 0 1,alignx right,growx 0");

        //---- startingFrameSpinner ----
        startingFrameSpinner.setModel(new SpinnerNumberModel(0, 0, 100000, 1));
        add(startingFrameSpinner, "cell 1 1,growx");

        //---- searchFramesLabel ----
        searchFramesLabel.setText(bundle.getString("search.limit"));
        searchFramesLabel.setLabelFor(searchFramesSpinner);
        add(searchFramesLabel, "cell 0 2,alignx right,growx 0");

        //---- searchFramesSpinner ----
        searchFramesSpinner.setModel(new SpinnerNumberModel(5000, 0, 100000, 1));
        add(searchFramesSpinner, "cell 1 2,growx");
    }
}
