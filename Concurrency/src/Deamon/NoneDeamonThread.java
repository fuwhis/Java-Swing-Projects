/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deamon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quy Nguyen
 */
public class NoneDeamonThread extends Thread{
    
    public void run(){
        int i = 0; 
        
        while(i < 10){
            System.out.println(" - Hello from None Deamon Thread " + i++);
            
            try{
                Thread.sleep(1000); 
                
            } catch (InterruptedException e) {}
            System.out.println("\n==> None Deamon Thread is ending.\n");
        }
    }
}
