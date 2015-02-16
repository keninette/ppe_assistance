package com.mmi;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboLineRenderer extends JLabel implements ListCellRenderer {
	private String label;
	
	public void ComboBoxActionsRenderer() 
    {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }
	
	@Override
	public Component getListCellRendererComponent(  JList list,
										            Object value,
										            int index,
										            boolean isSelected,
										            boolean cellHasFocus)
        {
            if (isSelected) 
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else 
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            try
            {
                label = ((ComboLine)value).getItemLabel();
                this.setText(label);
            }
            catch(NullPointerException e)
            {
                this.setText("");
            }
            return this;
        }

}
