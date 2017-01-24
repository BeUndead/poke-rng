package com.com.poke.ui.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

class ApplicationPanel extends JPanel {

    private final ControlsPanel controlsPanel;
    private final ResultsPanel resultsPanel;

    ApplicationPanel() {

        this.resultsPanel = new ResultsPanel();
        this.controlsPanel = new ControlsPanel(resultsPanel, resultsPanel::clearResults);
        initComponents();
    }

    private void initComponents() {
        final JSplitPane splitPane = new JSplitPane();

        //======== this ========
        setLayout(new MigLayout(
                "insets 5,hidemode 3,align center center",
                "[fill,grow]",
                "[fill,grow]"));

        //======== splitPane ========
        {
            splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

            splitPane.setTopComponent(controlsPanel);
            splitPane.setBottomComponent(resultsPanel);

            final Dimension splitPanePreferredSize = new Dimension();
            splitPanePreferredSize.width =
                    Math.max(controlsPanel.getPreferredSize().width, resultsPanel.getPreferredSize().width);
            splitPanePreferredSize.height =
                    controlsPanel.getPreferredSize().height + resultsPanel.getPreferredSize().height;

            splitPane.setMinimumSize(splitPanePreferredSize);
            splitPane.setPreferredSize(splitPanePreferredSize);
        }

        final Insets splitPaneInsets = splitPane.getInsets();
        final Dimension appPanelPreferredSize = new Dimension();
        appPanelPreferredSize.width
                = splitPane.getPreferredSize().width + splitPaneInsets.left + splitPaneInsets.right;
        appPanelPreferredSize.height
                = splitPane.getPreferredSize().height + splitPaneInsets.top + splitPaneInsets.bottom;
        setPreferredSize(appPanelPreferredSize);
        setMinimumSize(appPanelPreferredSize);

        add(splitPane, "cell 0 0,grow");
    }
}
