package com.aal.huntthethimble.view;

import com.aal.huntthethimble.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin on 2016-01-03.
 */
public class SettingsPanel extends JPanel
{

    public static int settingsPanelPreferredWidth = 160;
    public static int settingsPanelPreferredHeight = 240;

    //labele
    private static final String rateString = "Rate";
    private static final String numberString = "Number";

    private Controller controller;

    //pola tekstowe
    private JTextField rateTextField;
    private JTextField numberTextField;

    //przyciski
    private JButton generate;
    private JButton solve;
    private JButton start;
    private JButton end;

    public SettingsPanel()
    {
        this.controller = controller;

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Settings"),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        rateTextField = new JTextField(6);
        numberTextField = new JTextField(6);

        JLabel rateFieldLabel = new JLabel(rateString + ": ");
        rateFieldLabel.setLabelFor(rateTextField);

        JLabel numberFieldLabel = new JLabel(numberString + ": ");
        rateFieldLabel.setLabelFor(numberTextField);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(rateFieldLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(rateTextField,gbc);

        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(numberFieldLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(numberTextField,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5,0,0,0);
        generate = new JButton("Generate!");

        this.add(generate,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        //gbc.gridwidth = 1;
        gbc.insets = new Insets(5,0,5,0);
        start = new JButton("Start");

        this.add(start,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0,0,5,0);
        end = new JButton("End");

        this.add(end,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        solve = new JButton("Solve!");

        this.add(solve,gbc);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 100);
    }
}
