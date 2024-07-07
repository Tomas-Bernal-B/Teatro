/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cesar
 */
public class Global {
    public void removeAllTable(JTable Table){
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        /// remover viejas celdas
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    
    public void addElementTable(JTable Table, Object[] dataObj){
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.addRow(dataObj);
    }
}
