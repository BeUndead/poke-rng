package com.com.poke.ui.components;

import com.com.poke.model.IVs;
import com.com.poke.model.Stat;
import com.com.poke.model.Stats;
import com.com.poke.search.context.SearchFilter;
import com.com.poke.ui.components.widgets.PairedIVSpinner;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import static com.com.poke.ui.components.widgets.PairedIVSpinner.PairMembershipType.Maximum;
import static com.com.poke.ui.components.widgets.PairedIVSpinner.PairMembershipType.Minimum;

class IVsFilterPanel extends JPanel implements Supplier<SearchFilter<IVs>> {

    private final PairedIVSpinner hpMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner hpMaxSpinner = new PairedIVSpinner(Maximum);
    private final PairedIVSpinner attMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner attMaxSpinner = new PairedIVSpinner(Maximum);
    private final PairedIVSpinner defMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner defMaxSpinner = new PairedIVSpinner(Maximum);
    private final PairedIVSpinner spAMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner spAMaxSpinner = new PairedIVSpinner(Maximum);
    private final PairedIVSpinner spDMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner spDMaxSpinner = new PairedIVSpinner(Maximum);
    private final PairedIVSpinner speMinSpinner = new PairedIVSpinner(Minimum, 0);
    private final PairedIVSpinner speMaxSpinner = new PairedIVSpinner(Maximum);

    IVsFilterPanel() {

        initComponents();
    }

    public SearchFilter<IVs> get() {

        return ivs -> {
            for (final Stat stat : Stats.values()) {
                final byte ivForStat = ivs.forStat(stat).value();

                final SpinnerPartnership partnership = this.getSpinnerPartnershipForStat(stat);
                if (ivForStat < partnership.getMin().getIV()
                        || ivForStat > partnership.getMax().getIV()) {
                    return false;
                }
            }
            return true;
        };
    }

    private SpinnerPartnership getSpinnerPartnershipForStat(final Stat stat) {

        if (!(stat instanceof Stats)) {
            throw new UnsupportedOperationException("Unkonwn 'Stat': " + Objects.toString(stat));
        }
        final Stats stats = (Stats) stat;
        switch (stats) {
            case HP:
                return new SpinnerPartnership(hpMinSpinner, hpMaxSpinner);
            case Attack:
                return new SpinnerPartnership(attMinSpinner, attMaxSpinner);
            case Defense:
                return new SpinnerPartnership(defMinSpinner, defMaxSpinner);
            case SpecialAttack:
                return new SpinnerPartnership(spAMinSpinner, spAMaxSpinner);
            case SpecialDefense:
                return new SpinnerPartnership(spDMinSpinner, spDMaxSpinner);
            case Speed:
                return new SpinnerPartnership(speMinSpinner, speMaxSpinner);

            default:
                throw new IllegalStateException("Unsupported 'Stats': " + Objects.toString(stats));
        }
    }

    private void initComponents() {

        hpMinSpinner.setPartner(hpMaxSpinner);
        hpMaxSpinner.setPartner(hpMinSpinner);
        attMinSpinner.setPartner(attMaxSpinner);
        attMaxSpinner.setPartner(attMinSpinner);
        defMinSpinner.setPartner(defMaxSpinner);
        defMaxSpinner.setPartner(defMinSpinner);
        spAMinSpinner.setPartner(spAMaxSpinner);
        spAMaxSpinner.setPartner(spAMinSpinner);
        spDMinSpinner.setPartner(spDMaxSpinner);
        spDMaxSpinner.setPartner(spDMinSpinner);
        speMinSpinner.setPartner(speMaxSpinner);
        speMaxSpinner.setPartner(speMinSpinner);

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        final JLabel minLabel = new JLabel();
        final JLabel maxLabel = new JLabel();
        final JLabel hpLabel = new JLabel();
        final JLabel attLabel = new JLabel();
        final JLabel defLabel = new JLabel();
        final JLabel spALabel = new JLabel();
        final JLabel spDLabel = new JLabel();
        final JLabel speLabel = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.ivs")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3",
                "[30!][45!][45!]",
                "[20!][30!][30!][30!][30!][30!][30!]"));

        //---- minLabel ----
        minLabel.setText(bundle.getString("misc.min"));
        add(minLabel, "cell 1 0,alignx center,growx 0");

        //---- maxLabel ----
        maxLabel.setText(bundle.getString("misc.max"));
        add(maxLabel, "cell 2 0,alignx center,growx 0");

        //---- hpLabel ----
        hpLabel.setText(bundle.getString("stat.hp"));
        add(hpLabel, "cell 0 1,alignx right,growx 0");
        add(hpMinSpinner, "cell 1 1,growx");
        add(hpMaxSpinner, "cell 2 1,growx");

        //---- attLabel ----
        attLabel.setText(bundle.getString("stat.attack"));
        add(attLabel, "cell 0 2,alignx right,growx 0");
        add(attMinSpinner, "cell 1 2,growx");
        add(attMaxSpinner, "cell 2 2,growx");

        //---- defLabel ----
        defLabel.setText(bundle.getString("stat.defense"));
        add(defLabel, "cell 0 3,alignx right,growx 0");
        add(defMinSpinner, "cell 1 3,growx");
        add(defMaxSpinner, "cell 2 3,growx");

        //---- spALabel ----
        spALabel.setText(bundle.getString("stat.specialAttack"));
        add(spALabel, "cell 0 4,alignx right,growx 0");
        add(spAMinSpinner, "cell 1 4,growx");
        add(spAMaxSpinner, "cell 2 4,growx");

        //---- spDLabel ----
        spDLabel.setText(bundle.getString("stat.specialDefense"));
        add(spDLabel, "cell 0 5,alignx right,growx 0");
        add(spDMinSpinner, "cell 1 5,growx");
        add(spDMaxSpinner, "cell 2 5,growx");

        //---- speLabel ----
        speLabel.setText(bundle.getString("stat.speed"));
        add(speLabel, "cell 0 6,alignx right,growx 0");
        add(speMinSpinner, "cell 1 6,growx");
        add(speMaxSpinner, "cell 2 6,growx");
    }

    private static class SpinnerPartnership {

        private final PairedIVSpinner min;
        private final PairedIVSpinner max;

        private SpinnerPartnership(final PairedIVSpinner min, final PairedIVSpinner max) {

            this.min = min;
            this.max = max;
        }

        private PairedIVSpinner getMin() {

            return this.min;
        }

        private PairedIVSpinner getMax() {

            return this.max;
        }
    }
}
