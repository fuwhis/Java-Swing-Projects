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
public class DeamonThread extends Thread{
    
    @Override
    public void run(){
        int count = 0;
        
        while(true){
            System.out.println(" + Hello from Deamon Thread " + count++);
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
         }
    }
}
