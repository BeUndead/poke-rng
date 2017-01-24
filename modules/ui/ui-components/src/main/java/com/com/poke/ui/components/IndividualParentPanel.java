package com.com.poke.ui.components;

import com.com.poke.model.*;
import com.com.poke.search.context.IndividualParentContext;
import com.com.poke.search.context.SimpleIndividualParentContext;
import com.com.poke.ui.components.widgets.EnumeratedJComboBox;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class IndividualParentPanel extends JPanel implements Supplier<IndividualParentContext> {

    private final IVsPanel ivsPanel = new IVsPanel();
    private final EnumeratedJComboBox<AbilitySlot> abilityComboBox = new EnumeratedJComboBox<>(AbilitySlots.class);
    private final EnumeratedJComboBox<Nature> natureComboBox = new EnumeratedJComboBox<>(Natures.class);
    private final EnumeratedJComboBox<Ball> ballComboBox = new EnumeratedJComboBox<>(Balls.class);
    private final EnumeratedJComboBox<BreedingItem> itemComboBox = new EnumeratedJComboBox<>(BreedingItems.class);
    private final JCheckBox isDittoCheckBox = new JCheckBox();

    IndividualParentPanel() {

        initComponents();
    }

    private IVs getIVs() {

        return this.ivsPanel.get();
    }

    public IndividualParentContext get() {

        return new SimpleIndividualParentContext(
                this.itemComboBox.get(),
                this.abilityComboBox.get(),
                this.getIVs(),
                this.natureComboBox.get(),
                this.ballComboBox.get(),
                this.isDitto()
        );
    }

    private boolean isDitto() {

        return this.isDittoCheckBox.isSelected();
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        final JLabel abilityLabel = new JLabel();
        final JLabel natureLabel = new JLabel();
        final JLabel ballLabel = new JLabel();
        final JLabel itemLabel = new JLabel();

        //======== this ========

        setLayout(new MigLayout(
                "fill,insets 5,hidemode 3,align center center",
                "[40:50:70][110:130:130][40:50:70][130:170:170]",
                "[70!][30!][30!][30!]"));
        add(ivsPanel, "cell 0 0 4 1,alignx center,growx 0");

        //---- abilityLabel ----
        abilityLabel.setText(bundle.getString("attribute.ability"));
        abilityLabel.setLabelFor(abilityComboBox);
        add(abilityLabel, "cell 0 1,alignx right,growx 0");
        abilityComboBox.setSelectedItem(AbilitySlots.Hidden);
        add(abilityComboBox, "cell 1 1,growx");

        //---- natureLabel ----
        natureLabel.setText(bundle.getString("attribute.nature"));
        natureLabel.setLabelFor(natureComboBox);
        add(natureLabel, "cell 2 1,alignx right,growx 0");
        add(natureComboBox, "cell 3 1,growx");

        //---- ballLabel ----
        ballLabel.setText(bundle.getString("attribute.ball"));
        ballLabel.setLabelFor(ballComboBox);
        add(ballLabel, "cell 0 2,alignx right,growx 0");
        add(ballComboBox, "cell 1 2,growx");

        //---- itemLabel ----
        itemLabel.setText(bundle.getString("attribute.item"));
        itemLabel.setLabelFor(itemComboBox);
        add(itemLabel, "cell 2 2,alignx right,growx 0");
        add(itemComboBox, "cell 3 2,growx");

        //---- isDittoCheckBox ----
        isDittoCheckBox.setText(bundle.getString("species.ditto"));
        isDittoCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        add(isDittoCheckBox, "cell 3 3 1 1,alignx right,growx 0");
    }
}
