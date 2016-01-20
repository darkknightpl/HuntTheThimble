package com.aal.huntthethimble.view;

import javax.swing.*;

/**
 * Created by Marcin on 2016-01-04.
 */
public class MenuBar extends JMenuBar
{
    private JMenu menu;
    private JMenuItem helpMenu;
    private JMenuItem aboutMenu;

    public MenuBar()
    {
        menu = new JMenu("Menu");

        helpMenu = new JMenuItem("Help");

        helpMenu.setName("Help");
        menu.add(helpMenu);

        aboutMenu = new JMenuItem("About");
        aboutMenu.setName("About");

        menu.add(aboutMenu);

        this.add(menu);
    }
}
