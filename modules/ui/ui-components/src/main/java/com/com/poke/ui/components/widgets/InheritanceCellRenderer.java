package com.com.poke.ui.components.widgets;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ResourceBundle;

public class InheritanceCellRenderer implements TableCellRenderer {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("uiLocalisation");
    private static final Color MALE = new Color(Integer.parseInt(BUNDLE.getString("color.male"), 16));
    private static final Color FEMALE = new Color(Integer.parseInt(BUNDLE.getString("color.female"), 16));
    private final DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(
            final JTable table,
            final Object value,
            final boolean isSelected,
            final boolean hasFocus,
            final int row,
            final int column) {

        CombinedInheritanceResult<?> inheritedResult = (CombinedInheritanceResult<?>) value;
        if (inheritedResult == null) {
            inheritedResult = new CombinedInheritanceResult<>("", null);
        }

        final Component cell = defaultTableCellRenderer.getTableCellRendererComponent(
                table, inheritedResult.getResult(), isSelected, hasFocus, row, column);

        if (inheritedResult.fromMale()) {
            cell.setForeground(MALE);
        } else if (inheritedResult.fromFemale()) {
            cell.setForeground(FEMALE);
        } else {
            cell.setForeground(Color.black);
        }

        return cell;
    }

}
