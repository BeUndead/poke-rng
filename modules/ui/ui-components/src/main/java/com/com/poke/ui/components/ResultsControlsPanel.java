package com.com.poke.ui.components;

import com.com.poke.search.compute.FilteredOffspringSpreadCalculator;
import com.com.poke.search.compute.Gen7FilteredOffspringSpreadCalculator;
import com.com.poke.search.compute.SearchResult;
import com.com.poke.search.context.FilterContext;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.SearchContext;
import com.com.poke.search.context.TrainerContext;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;

class ResultsControlsPanel extends JPanel
        implements Supplier<Future<Collection<SearchResult>>> {

    private final FilteredOffspringSpreadCalculator calculator = new Gen7FilteredOffspringSpreadCalculator();

    private final JButton clearResultsButton = new JButton();
    private final JButton performSearchButton = new JButton();

    private final Supplier<ParentsContext> parentsContextSupplier;
    private final Supplier<TrainerContext> trainerContextSupplier;
    private final Supplier<SearchContext> searchContextSupplier;
    private final Supplier<FilterContext> filterContextSupplier;

    private final Consumer<Future<Collection<SearchResult>>> resultsConsumer;

    private final Runnable resultsClearer;

    ResultsControlsPanel(
            final Supplier<ParentsContext> parentsContextSupplier,
            final Supplier<TrainerContext> trainerContextSupplier,
            final Supplier<SearchContext> searchContextSupplier,
            final Supplier<FilterContext> filterContextSupplier,
            final Consumer<Future<Collection<SearchResult>>> resultsConsumer,
            final Runnable resultsClearer) {

        this.parentsContextSupplier = parentsContextSupplier;
        this.trainerContextSupplier = trainerContextSupplier;
        this.searchContextSupplier = searchContextSupplier;
        this.filterContextSupplier = filterContextSupplier;

        this.resultsConsumer = resultsConsumer;

        this.resultsClearer = resultsClearer;

        initComponents();
    }

    @Override
    public Future<Collection<SearchResult>> get() {

        final CompletableFuture<Collection<SearchResult>> resultsFuture = new CompletableFuture<>();
        SwingUtilities.invokeLater(() -> resultsFuture.complete(performSearch()));

        return resultsFuture;
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        //======== this ========

        setLayout(new MigLayout(
                "insets 5,hidemode 3,rtl,btt",
                "[60:80:100][60:80:100]",
                "[30!]"));

        // Note: Going rtl, so grid is reversed.
        //---- clearResultsButton ----
        clearResultsButton.setText(bundle.getString("misc.clear"));
        add(clearResultsButton, "cell 1 0,growx");

        //---- performSearchButton ----
        performSearchButton.setText(bundle.getString("misc.start"));
        add(performSearchButton, "cell 0 0,growx");

        configureListeners();
    }

    private void configureListeners() {

        performSearchButton.addActionListener(actionEvent -> resultsConsumer.accept(get()));

        clearResultsButton.addActionListener(actionEvent -> resultsClearer.run());
    }

    private Collection<SearchResult> performSearch() {

        return calculator.performSearch(
                parentsContextSupplier.get(),
                trainerContextSupplier.get(),
                searchContextSupplier.get(),
                filterContextSupplier.get()
        );
    }
}
