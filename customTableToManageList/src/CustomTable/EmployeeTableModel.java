/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomTable;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Quy Nguyen
 */
public class EmployeeTableModel <E> extends AbstractTableModel{
    String[] header;
    int[] indexes;
    Vector<Employee> data;          //actual data
    Vector<Employee> displayData;   //data for displaying, very handy for 
                                    //search and paging

    public EmployeeTableModel(String[] header, int[] indexes) {
        int i=0;
        this.header = new String[header.length];
        this.indexes = new int[indexes.length];
        for (i=0; i<header.length; i++) 
            this.header[i]=header[i];
        for (i=0; i<indexes.length; i++) 
            this.indexes[i]=indexes[i];
        this.data = new Vector<Employee>();
        this.displayData = new Vector<Employee>();
    }
    
    /**
     * Return the vector data to get informations
     * @return 
     */
    public Vector<Employee> getData(){
        return data;
    }
    
    public Vector<Employee> getDisplay(){
        return displayData;
    }
    
    @Override
    public int getRowCount() {
        return getDisplay().size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column){
        return (column>=0 && column<header.length)? header[column]:"";
    }
    
    
    @Override
    public Object getValueAt(int row, int column) {
        if (row<0 || row>=displayData.size() || 
            column<0 || column>=header.length) return null;
        Employee emp = getDisplay().get(row);
        switch (indexes[column]){
            case 0: return emp.getCode();
            case 1: return emp.getName();
            case 2: return emp.getAddress();
            case 3: return emp.isSex();
            case 4: return emp.getSalary();
        }
        return null;
    }
    
}
