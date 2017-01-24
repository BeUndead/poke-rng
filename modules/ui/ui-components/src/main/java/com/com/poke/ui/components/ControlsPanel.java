package com.com.poke.ui.components;

import com.com.poke.search.compute.SearchResult;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.function.Consumer;

class ControlsPanel extends JPanel {

    private final ParentsPanel parentsPanel;
    private final TrainerPanel trainerPanel;
    private final FilterPanel filterPanel;
    private final SearchPanel searchPanel;

    private final ResultsControlsPanel resultsControlsPanel;

    ControlsPanel(final Consumer<Future<Collection<SearchResult>>> resultsConsumer,
                  final Runnable resultsClearanceRunnable) {

        this.parentsPanel = new ParentsPanel();
        this.trainerPanel = new TrainerPanel();
        this.filterPanel = new FilterPanel(trainerPanel);
        this.searchPanel = new SearchPanel();
        this.resultsControlsPanel = new ResultsControlsPanel(
                parentsPanel,
                trainerPanel,
                searchPanel,
                filterPanel,
                resultsConsumer,
                resultsClearanceRunnable);

        initComponents();
    }

    private void initComponents() {

        //======== this ========
        setLayout(new MigLayout("insets 5,hidemode 3",
                "[grow,fill][grow,fill][grow,fill]",
                "[grow,fill][grow,fill][grow,fill]"));

        //---- panels ----
        add(parentsPanel, "cell 0 0 1 2");
        add(filterPanel, "cell 1 0");
        add(resultsControlsPanel, "cell 1 1");
        add(searchPanel, "cell 2 0");
        add(trainerPanel, "cell 2 1");
    }
}
