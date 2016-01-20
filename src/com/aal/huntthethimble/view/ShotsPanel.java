package com.aal.huntthethimble.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.*;

/**
 * Created by Marcin on 2016-01-05.
 */
public class ShotsPanel extends JPanel
{
    private TableModel tableModel;
    private JTable table;

    public ShotsPanel()
    {
        this.setLayout(new GridLayout(0,1));

        tableModel = new ShotsTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPanel = new JScrollPane(table);

        this.add(scrollPanel);
    }

    public void refreshTable()
    {
        //table.fireTableDataChanged();
    }
}

class ShotsTableModel extends AbstractTableModel
{
    @Override
    public int getRowCount() {
        return 50;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if(columnIndex == 0)
            return rowIndex + 1;
        else
            return "";
    }

    @Override
    public String getColumnName(int column)
    {

        String name;
        switch (column)
        {
            case 0:
                name = "Number";
                break;
            case 1:
                name = "Coordinates";
                break;
            case 2:
                name = "Hint";
                break;
            case 3:
                name = "Pick's";
                break;
            case 4:
                name = "Linear";
                break;
            default:
                name = super.getColumnName(column);
        }
        return name;
    }

}