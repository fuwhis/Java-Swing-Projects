/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Quy Nguyen
 */
public class InputValidator {
    static Border redBorder = BorderFactory.createLineBorder(Color.red,2);
    static Border defaultBorder = new JTextField().getBorder();
    /**
     * This method is used to validate email address. Please note that 
     * this is a <b>trial version</a>, please visit <a href ='my_website.com'> here </a> 
     * @param email the email you want to validate
     * @return true/false/ accordingly
     */
    //User method email checking
    public static boolean checkEmail(String email){
        return email.matches("[a-zA-Z0-9_.-]{3,50}@[a-zA-Z1-9-]{3,30}[.][a-z1-9]{2,5}([.][a-z1-9]{2,5})?");
    }
    
    /**
     * This method is used for desktop only
     */
    public static boolean checkName(JTextField nameField){
        String name = nameField.getText();
        if(!name.matches("[a-zA-Z1-9\\s]{3,50}")){
            nameField.setBorder(redBorder);
            nameField.requestFocusInWindow();
            return false; 
        } nameField.setBorder(defaultBorder);
        return true; 
    }
    
    public static boolean checkCode(JTextField codeField){
        String code = codeField.getText(); 
        if(!code.matches("(SE|IA|FSB)\\d{5}")){
            codeField.setBorder(redBorder);
            codeField.requestFocus();
            return false; 
        }codeField.setBorder(defaultBorder);
        return true; 
    }
    public static boolean checkEmail(JTextField emailField){
        String email = emailField.getText();
        if(!email.matches("[a-zA-Z0-9_.-]{3,50}@[a-zA-Z1-9-]{3,30}[.][a-z1-9]{2,5}([.][a-z1-9]{2,5})?")) {
            emailField.setBorder(redBorder);
            emailField.requestFocus();
            return false;
        }emailField.setBorder(defaultBorder);
        return true;
    }
    
    public static boolean checkPhone(JTextField phoneField){
        String phone = phoneField.getText();
        if(!phone.matches("[0-9]{9,11}")){
            phoneField.setBorder(redBorder);
            phoneField.requestFocus();
            return false;
        } phoneField.setBorder(defaultBorder);
        return true;
    }
    //defaut method email checking
    public static boolean checkEmail(String email,String pattern){
        return email.matches(pattern);
    }
}
