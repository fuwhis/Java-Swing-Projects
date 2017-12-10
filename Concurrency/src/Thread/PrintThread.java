package Thread;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quy Nguyen
 */
public class PrintThread extends Thread {
    
    @Override
    public void run(){
        try{
            System.out.println("PrintThread's id: " + Thread.currentThread().getId());
            for (int i = 0; i < 5; i++){
                System.out.println("PrintThread: " + i); 
                Thread.sleep(1000); //~1s
            }
        } catch (Exception e){
            
        }
    }
}
