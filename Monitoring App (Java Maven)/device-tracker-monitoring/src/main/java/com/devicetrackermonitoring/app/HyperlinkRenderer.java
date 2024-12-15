package com.devicetrackermonitoring.app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class HyperlinkRenderer implements TableCellRenderer {
    
    private JLabel label;

    public HyperlinkRenderer() {
        label = new JLabel();
        label.setForeground(Color.BLUE);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setOpaque(false);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText((value == null) ? "" : value.toString());
        return label;
    }
    
}