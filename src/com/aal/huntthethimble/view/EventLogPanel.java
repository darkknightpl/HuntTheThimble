package com.aal.huntthethimble.view;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Marcin on 2016-01-04.
 */
public class EventLogPanel extends JPanel
{
    private JTextPane logsPanel;
    private DefaultStyledDocument doc;
    private StyleContext sc;
    Calendar cal;// = Calendar.getInstance();
    SimpleDateFormat sdf;// = new SimpleDateFormat("HH:mm:ss");

    public EventLogPanel()
    {
        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        logsPanel = new JTextPane(doc);
        logsPanel.setEditable(false);

        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");

        this.setLayout(new GridLayout(0,1));

        JScrollPane scrollPanel = new JScrollPane(logsPanel);
        scrollPanel.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setPreferredSize(new Dimension(840 , 90));
        scrollPanel.setMinimumSize(new Dimension(10, 10));

        this.add(scrollPanel);
        this.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createEmptyBorder(1, 1, 1, 1),
                       BorderFactory.createTitledBorder("Event Log")));

    }

    public void displayString(String string)
    {
        String logMessage = sdf.format(cal.getTime()) + " " + string + '\n';

        try {
            doc.insertString(doc.getLength(),logMessage,null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(840,120);
    }
}
