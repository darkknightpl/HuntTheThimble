package com.aal.huntthethimble.view;

import com.aal.huntthethimble.controller.Controller;
import com.aal.huntthethimble.model.Model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Marcin on 2016-01-03.
 */
public class View
{
    private Controller controller;
    private Model model;

    private JFrame mainframe;
    private JPanel contentPanel;

    private MenuBar menuBar;
    private ButtonsPanel buttonsPanel;
    private EventLogPanel eventLogPanel;
    private ShotsPanel shotsPanel;
    private PicksTheoremPanel picksTheoremPanel;
    private LinearEquationsMethodPanel linearEquationsMethodPanel;


    public View(Model model, Controller controller)
    {
        mainframe = new JFrame("HuntTheTimble");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLayout(new BorderLayout());

        contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(0,1,0,1);
        contentPanel.setBorder(padding);
        contentPanel.setLayout(new BorderLayout());
        mainframe.setContentPane(contentPanel);

        menuBar = new MenuBar();
        buttonsPanel = new ButtonsPanel();
        eventLogPanel = new EventLogPanel();
        picksTheoremPanel = new PicksTheoremPanel();
        linearEquationsMethodPanel = new LinearEquationsMethodPanel();
        shotsPanel = new ShotsPanel();

        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(800,800));
        tabs.setFont( new Font("Tabs", Font.BOLD, 13) );

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();

        tab1.add(picksTheoremPanel);
        tab2.add(linearEquationsMethodPanel);

        tabs.addTab("Pick's Theorem", tab1);
        tabs.addTab("Linear Equations Method", tab2);

        contentPanel.add(tabs, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.WEST);
        contentPanel.add(eventLogPanel, BorderLayout.SOUTH);
        //change
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(shotsPanel,BorderLayout.CENTER);
        JButton button1 = new JButton("push me");
        JButton button2 = new JButton("push me");
        JPanel buttonNextPrev = new JPanel();
        buttonNextPrev.add(button1);
        buttonNextPrev.add(button2);
        rightPanel.add(buttonNextPrev, BorderLayout.SOUTH);
        contentPanel.add(rightPanel,BorderLayout.EAST);
        //contentPanel.add(shotsPanel, BorderLayout.EAST);
        //endofchange
        for(int i=0; i<200; i++)
            eventLogPanel.displayString("event" + i);

        mainframe.setJMenuBar(menuBar);
        //mainframe.setPreferredSize(new Dimension(1000,1000));
        mainframe.pack();
        mainframe.setLocation(10,10);
        mainframe.setVisible(true);

    }
}

