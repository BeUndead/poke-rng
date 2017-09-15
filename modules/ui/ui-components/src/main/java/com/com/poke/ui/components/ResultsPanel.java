package com.com.poke.ui.components;

import com.com.poke.model.IVs;
import com.com.poke.model.Stats;
import com.com.poke.search.compute.OffspringSpread;
import com.com.poke.search.compute.SearchResult;
import com.com.poke.ui.components.widgets.CombinedInheritanceResult;
import com.com.poke.ui.components.widgets.InheritanceCellRenderer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import static com.com.poke.ui.components.widgets.EnumeratedJComboBox.getBundleKey;

class ResultsPanel extends JPanel implements Consumer<Future<Collection<SearchResult>>> {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("uiLocalisation");

    private final JTable resultsTable = new JTable();

    ResultsPanel() {

        initComponents();
    }

    @Override
    public void accept(final Future<Collection<SearchResult>> collectionFuture) {

        SwingUtilities.invokeLater(() -> {
            clearResults();
            try {
                final Collection<SearchResult> results = collectionFuture.get();

                for (final SearchResult result : results) {
                    addResult(result);
                }
            } catch (final Throwable exception) {
                exception.printStackTrace();
            }
        });
    }

    void clearResults() {

        final DefaultTableModel defaultTableModel = (DefaultTableModel) resultsTable.getModel();
        for (int i = defaultTableModel.getRowCount() - 1; i >= 0; i--) {
            defaultTableModel.removeRow(i);
        }
    }

    private void addResult(final SearchResult result) {

        final DefaultTableModel defaultTableModel = (DefaultTableModel) resultsTable.getModel();

        final OffspringSpread spread = result.offspringSpread();
        final IVs ivs = spread.ivs();

        defaultTableModel.addRow(
                new Object[]{
                        result.frameFromInitial(),
                        BUNDLE.getString(getBundleKey(spread.abilitySlot())),
                        new CombinedInheritanceResult<>(
                                BUNDLE.getString(getBundleKey(spread.nature())), spread.natureInheritance()),
                        BUNDLE.getString(getBundleKey(spread.gender())),
                        new CombinedInheritanceResult<>(
                                BUNDLE.getString(getBundleKey(spread.ball())), spread.ballInheritance()),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.HP).value(), spread.ivInheritanceForStat(Stats.HP)),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.Attack).value(), spread.ivInheritanceForStat(Stats.Attack)),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.Defense).value(), spread.ivInheritanceForStat(Stats.Defense)),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.SpecialAttack).value(),
                                spread.ivInheritanceForStat(Stats.SpecialAttack)),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.SpecialDefense).value(),
                                spread.ivInheritanceForStat(Stats.SpecialDefense)),
                        new CombinedInheritanceResult<>(
                                ivs.forStat(Stats.Speed).value(), spread.ivInheritanceForStat(Stats.Speed)),
                        spread.pid().isPresent() ? Integer.toHexString(spread.pid().getAsInt()).toUpperCase() : "???",
                        spread.shinyValue().isPresent() ? spread.shinyValue().getAsInt() : "???",
                        Integer.toHexString(spread.encryptionConstant()).toUpperCase(),
                        result.rngAdvancements(),
                        stateToString(result.initialSeed())
                }
        );

    }

    private void initComponents() {

        final JScrollPane resultsScrollPane = new JScrollPane();

        //======== this ========

        setLayout(new MigLayout(
                "fill,insets 5,hidemode 3,align center center",
                "[fill]",
                "[grow,fill]"));

        //======== resultsScrollPane ========
        {
            setBorder(new TitledBorder(BUNDLE.getString("context.results")));

            //---- resultsTable ----
            resultsTable.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                            BUNDLE.getString("attribute.frame"),
                            BUNDLE.getString("attribute.ability"),
                            BUNDLE.getString("attribute.nature"),
                            BUNDLE.getString("attribute.gender"),
                            BUNDLE.getString("attribute.ball"),
                            BUNDLE.getString("stat.hp"),
                            BUNDLE.getString("stat.attack"),
                            BUNDLE.getString("stat.defense"),
                            BUNDLE.getString("stat.specialAttack"),
                            BUNDLE.getString("stat.specialDefense"),
                            BUNDLE.getString("stat.speed"),
                            BUNDLE.getString("misc.pid"),
                            BUNDLE.getString("misc.sv"),
                            BUNDLE.getString("misc.encryptionConstant"),
                            BUNDLE.getString("misc.used"),
                            BUNDLE.getString("context.seed")
                    }
            ) {
                private final Class<?>[] columnTypes = new Class<?>[]{
                        Long.class,
                        String.class,
                        CombinedInheritanceResult.class,
                        String.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        CombinedInheritanceResult.class,
                        String.class,
                        Integer.class,
                        String.class,
                        Integer.class,
                        String.class
                };

                @Override
                public Class<?> getColumnClass(int columnIndex) {

                    return columnTypes[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }

            });

            final int minimumViewportWidth;
            final int preferredViewportWidth;
            final int maximumViewportWidth;
            {
                final TableColumnModel columnModel = resultsTable.getColumnModel();

                // Frame
                columnModel.getColumn(0).setMinWidth(50);
                columnModel.getColumn(0).setMaxWidth(80);
                columnModel.getColumn(0).setPreferredWidth(60);
                // Ability
                columnModel.getColumn(1).setMinWidth(50);
                columnModel.getColumn(1).setMaxWidth(80);
                columnModel.getColumn(1).setPreferredWidth(60);
                // Nature
                columnModel.getColumn(2).setMinWidth(65);
                columnModel.getColumn(2).setMaxWidth(110);
                columnModel.getColumn(2).setPreferredWidth(80);
                // Gender
                columnModel.getColumn(3).setMinWidth(60);
                columnModel.getColumn(3).setMaxWidth(90);
                columnModel.getColumn(3).setPreferredWidth(60);
                // Ball
                columnModel.getColumn(4).setMinWidth(75);
                columnModel.getColumn(4).setMaxWidth(110);
                columnModel.getColumn(4).setPreferredWidth(80);
                // IVs
                // HP
                columnModel.getColumn(5).setMinWidth(50);
                columnModel.getColumn(5).setMaxWidth(70);
                columnModel.getColumn(5).setPreferredWidth(50);
                // Attack
                columnModel.getColumn(6).setMinWidth(50);
                columnModel.getColumn(6).setMaxWidth(70);
                columnModel.getColumn(6).setPreferredWidth(50);
                // Defense
                columnModel.getColumn(7).setMinWidth(50);
                columnModel.getColumn(7).setMaxWidth(70);
                columnModel.getColumn(7).setPreferredWidth(50);
                // Special Attack
                columnModel.getColumn(8).setMinWidth(50);
                columnModel.getColumn(8).setMaxWidth(70);
                columnModel.getColumn(8).setPreferredWidth(50);
                // Special Defense
                columnModel.getColumn(9).setMinWidth(50);
                columnModel.getColumn(9).setMaxWidth(70);
                columnModel.getColumn(9).setPreferredWidth(50);
                // Speed
                columnModel.getColumn(10).setMinWidth(50);
                columnModel.getColumn(10).setMaxWidth(70);
                columnModel.getColumn(10).setPreferredWidth(50);
                // PID
                columnModel.getColumn(11).setMinWidth(80);
                columnModel.getColumn(11).setMaxWidth(130);
                columnModel.getColumn(11).setPreferredWidth(100);
                // SV
                columnModel.getColumn(12).setMinWidth(60);
                columnModel.getColumn(12).setMaxWidth(90);
                columnModel.getColumn(12).setPreferredWidth(70);
                // Encryption Constant
                columnModel.getColumn(13).setMinWidth(80);
                columnModel.getColumn(13).setMaxWidth(130);
                columnModel.getColumn(13).setPreferredWidth(100);
                // Advancements
                columnModel.getColumn(14).setMinWidth(50);
                columnModel.getColumn(14).setMaxWidth(130);
                columnModel.getColumn(14).setPreferredWidth(100);
                // Seed
//                columnModel.getColumn(15).setMinWidth(20);
//                columnModel.getColumn(15).setMaxWidth(350);
//                columnModel.getColumn(15).setPreferredWidth(100);

                columnModel.getColumn(15).sizeWidthToFit();


                int minWidth = 0;
                int prefWidth = 0;
                int maxWidth = 0;
                for (int columnNumber = 0; columnNumber < columnModel.getColumnCount(); columnNumber++) {
                    final TableColumn column = columnModel.getColumn(columnNumber);
                    minWidth += column.getMinWidth();
                    prefWidth += column.getPreferredWidth();
                    maxWidth += column.getMaxWidth();
                }
                minimumViewportWidth = minWidth;
                preferredViewportWidth = prefWidth;
                maximumViewportWidth = maxWidth;
            }
            final Insets insets = getInsets();
            final Dimension minimumSize = new Dimension(minimumViewportWidth + insets.left + insets.right,
                    200 + insets.top + insets.bottom);
            final Dimension maximumSize = new Dimension(maximumViewportWidth + insets.left + insets.right,
                    1000 + insets.top + insets.bottom);

            resultsTable.setPreferredScrollableViewportSize(new Dimension(preferredViewportWidth, 200));
            resultsTable.setMinimumSize(minimumSize);
            resultsTable.setMaximumSize(maximumSize);

            resultsTable.setDefaultRenderer(CombinedInheritanceResult.class, new InheritanceCellRenderer());
            resultsTable.setDefaultRenderer(String.class, new DefaultTableCellRenderer());

            resultsTable.setShowVerticalLines(true);
            resultsTable.setShowGrid(true);
            resultsTable.setShowHorizontalLines(true);
            resultsScrollPane.setViewportView(resultsTable);
        }
        add(resultsScrollPane, "cell 0 0,align center center,growy");
    }


    /**
     * Converts the given {@code int array} to a hex-{@link String} (grouped in 8 character
     * groups, left-padded by 0s, and separated by spaces).
     *
     * @param state The {@code int array} to convert.
     *
     * @return The seed, represented as a readable {@code String}.
     */
    private static String stateToString(final int[] state) {
        return String.format("%08x %08x %08x %08x", state[0], state[1], state[2], state[3]).toUpperCase();
    }
}
