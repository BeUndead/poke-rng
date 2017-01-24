package com.com.poke.ui.components.widgets;

import com.com.poke.model.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class EnumeratedJComboBox<E> extends JComboBox<E> implements Supplier<E> {

    private final String keyPrefix;

    public EnumeratedJComboBox(final Class<? extends E> klass) {

        if (!klass.isEnum()) {
            throw new IllegalArgumentException("'klass' must be an Enum");
        }
        for (final E element : klass.getEnumConstants()) {
            this.addItem(element);
        }
        keyPrefix = getKeyPrefix(klass);
        this.setRenderer(this.new LocaleAwareComboBoxRenderer());
    }

    private static <T> String getKeyPrefix(final Class<? extends T> klass) {

        if (AbilitySlot.class.isAssignableFrom(klass)) {
            return "ability";
        }
        if (Ball.class.isAssignableFrom(klass)) {
            return "ball";
        }
        if (BreedingItem.class.isAssignableFrom(klass)) {
            return "item";
        }
        if (Gender.class.isAssignableFrom(klass)) {
            return "gender";
        }
        if (GenderRatio.class.isAssignableFrom(klass)) {
            return "genderRatio";
        }
        if (Nature.class.isAssignableFrom(klass)) {
            return "nature";
        }
        if (Stat.class.isAssignableFrom(klass)) {
            return "stat";
        }
        if (Type.class.isAssignableFrom(klass)) {
            return "type";
        }

        return "";
    }

    public static String getBundleKey(final Object value) {

        if (value == null) {
            return "";
        }
        return getKeyPrefix(value.getClass()) + "."
                + value.toString().substring(0, 1).toLowerCase() + value.toString().substring(1);
    }

    @Override
    public E get() {

        return this.getItemAt(this.getSelectedIndex());
    }

    private final class LocaleAwareComboBoxRenderer extends BasicComboBoxRenderer {

        private ResourceBundle bundle = ResourceBundle.getBundle("uiLocalisation");

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setFont(list.getFont());

            if (value instanceof Icon) {
                setIcon((Icon) value);
            } else {
                setText(bundle.getString(getBundleKey(value)));
            }
            return this;
        }
    }
}
