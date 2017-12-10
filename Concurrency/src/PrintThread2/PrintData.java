/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrintThread2;

/**
 *
 * @author Quy Nguyen
 */
public class PrintData {
    public synchronized void print(){ //Running
        try{
            System.out.println("PrintThread's ID: " + Thread.currentThread().getId());
            for (int i = 0; i < 10; i++){
                System.out.print("PrintThread: " + i); 
                Thread.sleep(1000); //~1s
            }
            System.out.print(","); 
        } catch (Exception e){            
        }
    } //Death
}
