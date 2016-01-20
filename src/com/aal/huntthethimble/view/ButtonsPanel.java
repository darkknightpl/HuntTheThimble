package com.aal.huntthethimble.view;

import com.aal.huntthethimble.controller.Controller;
import com.aal.huntthethimble.model.Model;
import com.aal.huntthethimble.view.resources.Icons;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Marcin on 2016-01-04.
 */
public class ButtonsPanel extends JPanel
{
    private Controller controller;
    private Model model;

    private JToolBar toolBar;

    private JButton playButton;
    private JButton loadTestButton;
    private JButton createTestButton;
    private JButton settingsButton;
    private JButton helpButton;

    private JFileChooser fileChooser;
    private ButtonsPanel me = this;

    public ButtonsPanel()
    {
        //this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), new SoftBevelBorder(SoftBevelBorder.RAISED)));

        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.LIGHT_GRAY)));

        toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        playButton = new JButton(Icons.PLAY);
        playButton.setPreferredSize(new Dimension(50,50));
        playButton.setToolTipText("Solve test case");

        loadTestButton = new JButton(Icons.LOAD);
        loadTestButton.setPreferredSize(new Dimension(50,50));
        loadTestButton.setToolTipText("Load another test case");
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        loadTestButton.addActionListener(loadTestListener());

        createTestButton = new JButton(Icons.CREATE);
        createTestButton.setPreferredSize(new Dimension(50,50));
        createTestButton.setToolTipText("Create new test case");

        settingsButton = new JButton(Icons.SETTINGS);
        settingsButton.setPreferredSize(new Dimension(50,50));
        settingsButton.setToolTipText("Change settings");

        helpButton = new JButton(Icons.HELP);
        helpButton.setPreferredSize(new Dimension(50,50));
        helpButton.setToolTipText("Help");

        //toolBar.addSeparator();
        toolBar.add(playButton);
        toolBar.add(loadTestButton);
        toolBar.add(createTestButton);
        toolBar.add(settingsButton);
        toolBar.add(helpButton);

        this.add(toolBar);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(60,600);
    }

    
    //// TODO: 2016-01-15 move to controller 
    public ActionListener loadTestListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showFileChooserWindow();
                //int result = fileChooser.showOpenDialog(ButtonsPanel.this);
                //if(result == JFileChooser.APPROVE_OPTION)

            }
        };
    }

    public void showFileChooserWindow()
    {
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
            model.loadFile(fileChooser.getSelectedFile());

    }
}
