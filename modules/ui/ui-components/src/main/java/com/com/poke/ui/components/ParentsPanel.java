package com.com.poke.ui.components;

import com.com.poke.model.GenderRatio;
import com.com.poke.model.GenderRatios;
import com.com.poke.search.context.IndividualParentContext;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.SimpleParentsContext;
import com.com.poke.ui.components.widgets.EnumeratedJComboBox;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class ParentsPanel extends JPanel implements Supplier<ParentsContext> {

    private final Color maleColor = Color.blue;
    private final Color femaleColor = Color.red;
    private final IndividualParentPanel maleParentPanel = new IndividualParentPanel();
    private final IndividualParentPanel femaleParentPanel = new IndividualParentPanel();
    private final EnumeratedJComboBox<GenderRatio> genderRatioComboBox = new EnumeratedJComboBox<>(GenderRatios.class);
    private final JCheckBox masudaCheckBox = new JCheckBox();
    private final JCheckBox homogeneousCheckBox = new JCheckBox();

    ParentsPanel() {

        initComponents();
    }

    public ParentsContext get() {

        return new SimpleParentsContext(
                this.getMaleParentContext(),
                this.getFemaleParentContext(),
                this.getGenderRatio(),
                this.satisfyMasudaMethodCriterion(),
                this.areHomogeneous()
        );
    }

    private IndividualParentContext getMaleParentContext() {

        return this.maleParentPanel.get();
    }

    private IndividualParentContext getFemaleParentContext() {

        return this.femaleParentPanel.get();
    }

    private GenderRatio getGenderRatio() {

        return this.genderRatioComboBox.getItemAt(this.genderRatioComboBox.getSelectedIndex());
    }

    private boolean satisfyMasudaMethodCriterion() {

        return this.masudaCheckBox.isSelected();
    }

    private boolean areHomogeneous() {

        return this.homogeneousCheckBox.isSelected();
    }

    private void initComponents() {

        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");
        final JLabel genderRatioLabel = new JLabel();

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.parents")));

        setLayout(new MigLayout(
                "fill,insets 5,hidemode 3,align center center",
                "[160:160:200][160:160:200]",
                "[190!][190!][35!][35!]"));

        final int maleColor = Integer.parseInt(bundle.getString("color.male"), 16);
        final int femaleColor = Integer.parseInt(bundle.getString("color.female"), 16);

        this.maleParentPanel.setBorder(new TitledBorder(
                null,
                bundle.getString("gender.male") + bundle.getString("context.parent"),
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.TOP,
                null,
                new Color(maleColor)));
        add(maleParentPanel, "cell 0 0 2 1,alignx center,growx 0");

        this.femaleParentPanel.setBorder(new TitledBorder(
                null,
                bundle.getString("gender.female") + bundle.getString("context.parent"),
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.TOP,
                null,
                new Color(femaleColor)));
        add(femaleParentPanel, "cell 0 1 2 1,alignx center,growx 0");

        //---- genderRatioLabel ----
        genderRatioLabel.setText(bundle.getString("attribute.genderRatio"));
        add(genderRatioLabel, "cell 0 2,alignx center,growx 0");

        //---- genderRatioComboBox ----
        genderRatioComboBox.setSelectedItem(GenderRatios.Even);
        add(genderRatioComboBox, "cell 1 2,growx");

        //---- masudaCheckBox ----
        masudaCheckBox.setText(bundle.getString("misc.masuda"));
        masudaCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        masudaCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        add(masudaCheckBox, "cell 0 3,growx");

        //---- homogeneousCheckBox ----
        homogeneousCheckBox.setText(bundle.getString("misc.homogeneous"));
        homogeneousCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        homogeneousCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        add(homogeneousCheckBox, "cell 1 3,growx");
    }
}
