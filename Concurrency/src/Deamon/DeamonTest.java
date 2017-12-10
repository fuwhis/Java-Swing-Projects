/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deamon;

/**
 *
 * @author Quy Nguyen
 */
public class DeamonTest {
    public static void main(String[] args){
        System.out.println("==> Main Thread is running ... \n");
        
        Thread deamonThread = new DeamonThread();
        
        deamonThread.setDaemon(true);
        deamonThread.start();
        
        new NoneDeamonThread().start();
        
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){}
        
        System.out.println("Main Thread is ending.\n");
    }
}
