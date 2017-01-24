package com.com.poke.ui.components;

import javax.swing.*;

public class ApplicationFrame extends JFrame implements Runnable {

    private final ApplicationPanel applicationPanel = new ApplicationPanel();

    public ApplicationFrame() {

        super();
        initComponents();
    }

    public void run() {

        this.setVisible(true);
    }

    private void initComponents() {

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(applicationPanel);

        this.pack();
        this.setMinimumSize(this.getSize());
        this.setPreferredSize(this.getSize());
    }
}
