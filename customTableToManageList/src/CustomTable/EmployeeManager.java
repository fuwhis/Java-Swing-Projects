/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Quy Nguyen
 */
public class EmployeeManager extends javax.swing.JFrame {

    String filename = "employee2.txt";
    EmployeeTableModel<Employee> model;
    boolean addNew = true;
    boolean changed = false;
    int firstOnPage = 0; //first employee displayed on page
    Stack<UndoInfo> undoDel;
    //DefaultTableModel searchModel;

   

    

    public EmployeeManager() {
        initComponents();
        undoDel = new Stack<>();
        int[] indexes = {0, 1, 4};
        String[] header = {"Code", "Name", "Salary"};
        model = new EmployeeTableModel<Employee>(header, indexes);
//        model = (DefaultTableModel)tblEmployee.getModel();
//        model = (EmployeeTableModel)tblEmployee.getModel();
        this.tblEmployee.setModel(model);
        loadData();
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbFemale);
        bg.add(rbMale);
        rbMale.setSelected(true);

        //autosave feature
        Thread autoSave = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }
                        btnSaveToFileActionPerformed(null);
                        lblTime.setText(new Date(System.currentTimeMillis()).toString());
                    }
                } catch (Exception e) {
                }
            }
        };
        autoSave.start();
    }

    private boolean validCode(String s) {
        int dataSize = model.getData().size();
        String codeCompare;
        //String s="";
        if (addNew == true) {
            //s = this.txtCode.getText().trim().toUpperCase();
            if (!s.matches("E\\d{3}")) {
                JOptionPane.showMessageDialog(this, "Code format: Exxx, where x is a number");
                return false;
            }
            for (int i = 0; i < dataSize; i++) {
                codeCompare = model.getData().get(i).getCode();
                if (s.equals(codeCompare)) {
                    JOptionPane.showMessageDialog(this, "Code duplicated");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validName(String s) {
        if (s.length() == 0) { //catch user is not inputting 
            JOptionPane.showMessageDialog(this, "Name is required");
            return false;
        }
        return true;
    }

    private boolean validSalary(String s) {
        //String s="";
        if (!s.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Salary is an integer number");
            return false;
        }
        return true;
    }

    /**
     * Load data to display, "from" element "to" element in data. Does not
     * exclude "to" like subList(fromIndex, toIndex) method.
     *
     * @param from start element
     * @param to end element
     */
    private void loadIntoDisplay(int from, int to) {
        int realDataSize = model.getData().size();
        while (to >= realDataSize) {
            to--; //avoids IndexOutOfBounds
        }
        if (from >= 0 && from <= to && to < realDataSize) {
            model.getDisplay().clear();
            model.getDisplay().addAll(model.getData().subList(from, to + 1));
        }
    }

    private void loadData() {
        boolean valid=true;
        try{
            File f = new File(filename);
            if (!f.exists()) return;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null && valid==true){
                StringTokenizer stk = new StringTokenizer(details, ",");
                String code = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String addr = stk.nextToken().trim();
                String sexStr = stk.nextToken().trim();
                boolean sex = (sexStr.equalsIgnoreCase("MALE")==true);
                String salaryStr = stk.nextToken().trim();
                if (!validCode(code) || !validName(name) || !validSalary(salaryStr)){ 
                    valid=false;
                }
                
                else{
                    //create a new row
                    int salary = Integer.parseInt(salaryStr);
                    Employee emp = new Employee(code,name,addr,sex,salary);
                    //add into Vector in EmployeeTableModel
                    model.getData().add(emp);
                }

            }
            br.close(); fr.close();
            if (valid==false) {
                JOptionPane.showMessageDialog(this, "Invalid file");
                model.getData().clear();
            }
            else{
                //add first 5 employees to display
                loadIntoDisplay(0, 4);
                firstOnPage=0;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid file. Format error");
        }

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnSaveToFile = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        rbFemale = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        rbMale = new javax.swing.JRadioButton();
        btnNextPage = new javax.swing.JButton();
        btnPrevPage = new javax.swing.JButton();
        btnUndoDel = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnSaveToFile.setText("Save to file");
        btnSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveToFileActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Employee Details"));

        jLabel5.setText("Address");

        jLabel1.setText("Code");

        jLabel2.setText("Name");

        rbFemale.setText("Female");

        jLabel4.setText("Sex");

        jLabel3.setText("Salary");

        rbMale.setText("Male");
        rbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(rbMale)
                                .addGap(18, 18, 18)
                                .addComponent(rbFemale))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        btnNextPage.setText("Next Page");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });

        btnPrevPage.setText("Previous Page");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });

        btnUndoDel.setText("Undo Delete");
        btnUndoDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPrevPage)
                                .addGap(18, 18, 18)
                                .addComponent(btnNextPage))
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSearch)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addGap(0, 664, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUndoDel)
                    .addComponent(btnNew))
                .addGap(50, 50, 50)
                .addComponent(btnRemove)
                .addGap(71, 71, 71)
                .addComponent(btnSaveToFile)
                .addGap(61, 61, 61)
                .addComponent(btnSave)
                .addGap(57, 57, 57)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNextPage)
                            .addComponent(btnPrevPage)
                            .addComponent(btnUndoDel)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnRemove)
                    .addComponent(btnSaveToFile)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addGap(35, 35, 35)
                .addComponent(lblTime)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //event handler
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        addNew = true; //switch to add new mode
        txtCode.setEditable(true); //allow user to change
        txtCode.setText("");
        txtCode.requestFocus();
        txtName.setText("");
        txtAddress.setText("");
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtSalary.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (changed == true) {
            int r = JOptionPane.showConfirmDialog(this, "Do you want to save data to file?",
                    "File changed", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                btnSaveToFileActionPerformed(evt);
                System.exit(0);
            } else if (r == JOptionPane.NO_OPTION) {
                System.exit(0);
            }

        } else {
            int r = JOptionPane.showConfirmDialog(this, "Exit application?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            int r = JOptionPane.showConfirmDialog(this, "Do you want to delete this employee?",
                    "Delete an employee", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                //get the id in data vector, then delete it
                //delete in actual data and in display data
                int selectedR = tblEmployee.getSelectedRow();
                if (selectedR == -1) {
                    JOptionPane.showMessageDialog(this, "No employee selected!");
                    return;
                }
                Employee selectedEmp = model.getDisplay().get(selectedR);
                if (!validName(selectedEmp.getName())) {
                    return;
                }
                UndoInfo el = new UndoInfo();
                int selectedEmpInx = model.getData().indexOf(selectedEmp);
                el.setEmp(selectedEmp);
                el.setEmpIndex(selectedEmpInx);
                //remove real employee in real data
                model.getData().remove(selectedEmpInx);
                model.getDisplay().remove(selectedR);
                tblEmployee.updateUI();
                changed = true;
                undoDel.push(el);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveToFileActionPerformed
        try {
            PrintWriter pw = new PrintWriter(filename);
            //save the actual data
            for (Employee aEmp : model.getData()) {
                String data = String.format("%s,%s,%s,%s,%s",
                        aEmp.getCode(), aEmp.getName(), aEmp.getAddress(),
                        aEmp.isSex() ? "MALE" : "FEMALE", String.valueOf(aEmp.getSalary()));
                pw.println(data);
            }
            pw.close();
            changed = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveToFileActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        String addr = txtAddress.getText().trim();
        boolean sex = this.rbMale.isSelected();
        String salaryStr = txtSalary.getText().trim();
        if (!validCode(code)) {
            return;
        }
        if (!validName(name)) {
            return;
        }
        if (!validSalary(salaryStr)) {
            return;
        }
        int salary = Integer.parseInt(salaryStr);
        Employee emp = new Employee(code, name, addr, sex, salary);
        if (addNew == true) {//add a new row
            model.getDisplay().add(emp);
            model.getData().add(emp);
            loadIntoDisplay(model.getData().size() / 5 + model.getData().size() % 5, model.getData().size());
        } else { //update selected row
            int pos = tblEmployee.getSelectedRow();
            //get the old employee data on row to find that employee's index
            //in actual data
            Employee oldEmp = model.getDisplay().get(pos);
            model.getDisplay().set(pos, emp);
            //get the actual position in real data array
            int posInData = model.getData().indexOf(oldEmp);
            model.getData().set(posInData, emp);
        }
        tblEmployee.updateUI();
        changed = true;
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        int selectedRow = tblEmployee.getSelectedRow();
        Employee curEmp = model.getDisplay().get(selectedRow);
        //get value from data, not from displayed row
        txtCode.setText(curEmp.getCode());
        txtName.setText(curEmp.getName());
        txtAddress.setText(curEmp.getAddress());
        rbMale.setSelected(curEmp.isSex());
        rbFemale.setSelected(!curEmp.isSex());
        txtSalary.setText(curEmp.getSalary() + "");
        addNew = false; // switch editing mode
        txtCode.setEditable(false); //prevent from editing code field
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //search action
        Vector<Employee> searchResult = new Vector<>();

        searchResult.clear();
        if (txtSearch.getText().isEmpty()) {
            this.loadIntoDisplay(0, 4);
            firstOnPage = 4;
            this.tblEmployee.setModel(model);
            tblEmployee.updateUI();
        } else {

            for (int i = 0; i < model.getData().size(); i++) {
                String name = model.getData().get(i).getName();
                if (name.indexOf(txtSearch.getText()) >= 0) {
                    searchResult.add(model.getData().get(i));
                }
            }
            model.getDisplay().clear();
            model.getDisplay().addAll(searchResult);
            tblEmployee.updateUI();
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    private void rbMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMaleActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        int dataSize = model.getData().size();
        //make button works properly
        //after next page, firstOnPage now point to the first employee on page
        if (firstOnPage < (dataSize - dataSize % 5)) {
            firstOnPage += 5;
        }
        loadIntoDisplay(firstOnPage, firstOnPage + 4);
        tblEmployee.updateUI();
    }//GEN-LAST:event_btnNextPageActionPerformed

    private void btnPrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageActionPerformed

        if (firstOnPage < 5) {
            loadIntoDisplay(0, 4);
        }
        if (firstOnPage > 4) {
            firstOnPage -= 5; //make button works properly
        } else {
            firstOnPage = 0;
        }
        loadIntoDisplay(firstOnPage, firstOnPage + 4);
        tblEmployee.updateUI();
    }//GEN-LAST:event_btnPrevPageActionPerformed

    private void btnUndoDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoDelActionPerformed
        if (undoDel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nothing to undo");
        } else {
            UndoInfo el = undoDel.pop();
            model.getData().add(el.getEmpIndex(), el.emp);
            //display restored page
            //get the first employee displayed on that page
            //ex: restored emp has index 13, so 13-13%5 = 10
            //->display from 10 to 14
            firstOnPage = el.getEmpIndex() - el.getEmpIndex() % 5;
            loadIntoDisplay(firstOnPage, firstOnPage + 4);
            tblEmployee.updateUI();
            //JOptionPane.showMessageDialog(this,"Employee restored");
        }
    }//GEN-LAST:event_btnUndoDelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveToFile;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUndoDel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
