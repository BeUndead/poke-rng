package com.com.poke.ui.components.widgets;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;

public class HexFormatterFactory extends DefaultFormatterFactory {

    @Override
    public JFormattedTextField.AbstractFormatter getDefaultFormatter() {

        return new HexFormatter();
    }
}
