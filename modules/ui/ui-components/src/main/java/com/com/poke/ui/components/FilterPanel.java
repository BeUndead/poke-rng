package com.com.poke.ui.components;

import com.com.poke.model.*;
import com.com.poke.search.context.FilterContext;
import com.com.poke.search.context.SearchFilter;
import com.com.poke.search.context.SimpleFilterContext;
import com.com.poke.search.context.TrainerContext;
import com.com.poke.ui.components.widgets.EnumeratedJComboBox;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.function.Supplier;

class FilterPanel extends JPanel implements Supplier<FilterContext> {

    private final IVsFilterPanel ivsFilterPanel = new IVsFilterPanel();

    // TODO - Make these support multiple values...
    private final EnumeratedJComboBox<Nature> natureComboBox = new EnumeratedJComboBox<>(Natures.class);
    private final EnumeratedJComboBox<AbilitySlot> abilityComboBox = new EnumeratedJComboBox<>(AbilitySlots.class);
    private final EnumeratedJComboBox<Gender> genderComboBox = new EnumeratedJComboBox<>(Genders.class);
    private final EnumeratedJComboBox<Type> hiddenPowerComboBox = new EnumeratedJComboBox<>(Types.class);
    private final EnumeratedJComboBox<Ball> ballComboBox = new EnumeratedJComboBox<>(Balls.class);

    private final JCheckBox isShinyCheckBox = new JCheckBox();

    private final JLabel natureLabel = new JLabel();
    private final JLabel abilityLabel = new JLabel();
    private final JLabel genderLabel = new JLabel();
    private final JLabel hiddenPowerLabel = new JLabel();
    private final JLabel ballLabel = new JLabel();

    private final Supplier<TrainerContext> trainerContextSupplier;

    FilterPanel(final Supplier<TrainerContext> trainerContextSupplier) {

        this.trainerContextSupplier = trainerContextSupplier;
        initComponents();
    }

    // TODO - Make these support multiple values...
    private static <T> SearchFilter<T> getSearchFilter(final EnumeratedJComboBox<T> comboBox) {

        if (comboBox.isEnabled()) {
            return item -> comboBox.get() == item;
        } else {
            return item -> true;
        }
    }

    @Override
    public FilterContext get() {

        return SimpleFilterContext.builder()
                .withIVsFilter(this.getIVsFilter())
                .withNatureFilter(this.getNatureFilter())
                .withBallFilter(this.getBallFilter())
                .withGenderFilter(this.getGenderFilter())
                .withHiddenPowerTypeFilter(this.getHiddenPowerTypeFilter())
                .withAbilitySlotFiter(this.getAbilityFilter())
                .withShinyValueFilter(this.getShinyValueFilter())
                .build();
    }

    private SearchFilter<Integer> getShinyValueFilter() {

        return sv -> !this.isShinyCheckBox.isSelected() || sv == this.trainerContextSupplier.get().shinyValue();
    }

    private SearchFilter<Nature> getNatureFilter() {

        return getSearchFilter(this.natureComboBox);
    }

    private SearchFilter<AbilitySlot> getAbilityFilter() {

        return getSearchFilter(this.abilityComboBox);
    }

    private SearchFilter<Gender> getGenderFilter() {

        return getSearchFilter(this.genderComboBox);
    }

    private SearchFilter<Type> getHiddenPowerTypeFilter() {

        return getSearchFilter(this.hiddenPowerComboBox);
    }

    private SearchFilter<Ball> getBallFilter() {

        return getSearchFilter(this.ballComboBox);
    }

    private SearchFilter<IVs> getIVsFilter() {

        return this.ivsFilterPanel.get();
    }

    private void initComponents() {
        final ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        //======== this ========
        setBorder(new TitledBorder(bundle.getString("context.filters")));

        setLayout(new MigLayout(
                "insets 5,hidemode 3",
                "[140:160:160][80:100:100][110:130:130]",
                "[20!][40!][40!][40!][40!][40!][40!]"));
        add(ivsFilterPanel, "cell 0 0 1 7");

        //---- natureLabel ----
        natureLabel.setText(bundle.getString("attribute.nature"));
        add(natureLabel, "cell 1 1,alignx right,growx 0");
        add(natureComboBox, "cell 2 1,growx");

        //---- abilityLabel ----
        abilityLabel.setText(bundle.getString("attribute.ability"));
        add(abilityLabel, "cell 1 2,alignx right,growx 0");
        add(abilityComboBox, "cell 2 2,growx");

        //---- genderLabel ----
        genderLabel.setText(bundle.getString("attribute.gender"));
        add(genderLabel, "cell 1 3,alignx right,growx 0");
        add(genderComboBox, "cell 2 3,growx");

        //---- hiddenPowerLabel ----
        hiddenPowerLabel.setText(bundle.getString("move.hiddenPower"));
        add(hiddenPowerLabel, "cell 1 4,alignx right,growx 0");
        add(hiddenPowerComboBox, "cell 2 4,growx");

        //---- ballLabel ----
        ballLabel.setText(bundle.getString("attribute.ball"));
        add(ballLabel, "cell 1 5,alignx right,growx 0");
        add(ballComboBox, "cell 2 5,growx");

        //---- isShinyCheckBox ----
        isShinyCheckBox.setText(bundle.getString("misc.shiny"));
        isShinyCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        add(isShinyCheckBox, "cell 1 6 2 1,alignx right,growx 0");

        configureListeners();
    }

    private void configureListeners() {

        configureEnabledListener(natureLabel, natureComboBox);
        configureEnabledListener(abilityLabel, abilityComboBox);
        configureEnabledListener(genderLabel, genderComboBox);
        configureEnabledListener(hiddenPowerLabel, hiddenPowerComboBox);
        configureEnabledListener(ballLabel, ballComboBox);
    }

    private void configureEnabledListener(final JLabel label, final JComponent component) {

        label.setEnabled(false);
        component.setEnabled(false);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                final boolean isEnabled = label.isEnabled();

                label.setEnabled(!isEnabled);
                component.setEnabled(!isEnabled);
            }
        });
    }

}
