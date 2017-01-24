package com.com.poke.ui.components;

import com.com.poke.model.CollectiveIVs;
import com.com.poke.model.IVs;
import com.com.poke.model.Stat;
import com.com.poke.model.Stats;
import com.com.poke.ui.components.widgets.IVSpinner;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class IVsPanel extends JPanel implements Supplier<IVs> {

    private final IVSpinner hpSpinner = new IVSpinner();
    private final IVSpinner attSpinner = new IVSpinner();
    private final IVSpinner defSpinner = new IVSpinner();
    private final IVSpinner spASpinner = new IVSpinner();
    private final IVSpinner spDSpinner = new IVSpinner();
    private final IVSpinner speSpinner = new IVSpinner();

    IVsPanel() {

        this.initComponents();
    }

    public final IVs get() {

        final byte[] ivs = new byte[6];
        for (final Stat stat : Stats.values()) {
            ivs[stat.index()] = this.getSpinnerForStat(stat).getIV();
        }
        return CollectiveIVs.from(ivs);
    }

    private IVSpinner getSpinnerForStat(final Stat stat) {

        if (!(stat instanceof Stats)) {
            throw new UnsupportedOperationException("Unknown 'Stat': " + Objects.toString(stat));
        }
        final Stats stats = (Stats) stat;
        switch (stats) {
            case HP:
                return this.hpSpinner;
            case Attack:
                return this.attSpinner;
            case Defense:
                return this.defSpinner;
            case SpecialAttack:
                return this.spASpinner;
            case SpecialDefense:
                return this.spDSpinner;
            case Speed:
                return this.speSpinner;

            default:
                throw new IllegalArgumentException("Unknown 'Stats': " + Objects.toString(stats));
        }
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        final JLabel hpLabel = new JLabel();
        final JLabel attLabel = new JLabel();
        final JLabel defLabel = new JLabel();
        final JLabel spALabel = new JLabel();
        final JLabel spDLabel = new JLabel();
        final JLabel speLabel = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.ivs")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3,aligny center",
                "rel[45:45,label]rel[45:45,label]rel[45:45,label]rel[45:45,label]rel[45:45,label]rel[45:45,label]",
                "[15:15][30:30,sizegroup 1]"));

        //---- hpLabel ----
        hpLabel.setText(bundle.getString("stat.hp"));
        hpLabel.setLabelFor(hpSpinner);
        add(hpLabel, "cell 0 0,alignx center,growx 0");

        //---- attLabel ----
        attLabel.setText(bundle.getString("stat.attack"));
        attLabel.setLabelFor(attSpinner);
        add(attLabel, "cell 1 0,align center center,grow 0 0");

        //---- defLabel ----
        defLabel.setText(bundle.getString("stat.defense"));
        defLabel.setLabelFor(defSpinner);
        add(defLabel, "cell 2 0,align center center,grow 0 0");

        //---- spALabel ----
        spALabel.setText(bundle.getString("stat.specialAttack"));
        spALabel.setLabelFor(spASpinner);
        add(spALabel, "cell 3 0,align center center,grow 0 0");

        //---- spDLabel ----
        spDLabel.setText(bundle.getString("stat.specialDefense"));
        spDLabel.setLabelFor(spDSpinner);
        add(spDLabel, "cell 4 0,align center center,grow 0 0");

        //---- speLabel ----
        speLabel.setText(bundle.getString("stat.speed"));
        speLabel.setLabelFor(speSpinner);
        add(speLabel, "cell 5 0,align center center,grow 0 0");

        //---- hpSpinner ----
        this.hpSpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.hpSpinner, "cell 0 1,growx");

        //---- attSpinner ----
        this.attSpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.attSpinner, "cell 1 1,growx");

        //---- defSpinner ----
        this.defSpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.defSpinner, "cell 2 1,growx");

        //---- spASpinner ----
        this.spASpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.spASpinner, "cell 3 1,growx");

        //---- spDSpinner ----
        this.spDSpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.spDSpinner, "cell 4 1,growx");

        //---- speSpinner ----
        this.speSpinner.setModel(new SpinnerNumberModel(31, 0, 31, 1));
        add(this.speSpinner, "cell 5 1,growx");
    }
}
