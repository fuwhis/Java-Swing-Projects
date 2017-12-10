/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Counter;

/**
 *
 * @author Quy Nguyen
 */
public class Threads extends Thread{
    Counter ct = new Counter(); 
    
    public Threads(Counter ct){
        this.ct = ct; 
    }
    
    
    @Override
    public void run(){
        try{
            System.out.println("(Thread's ID: " + Thread.currentThread().getId() + ")");
            for (int i = 0; i < 10; i++){
                ct.increase();
                System.out.println("Count = " + this.ct.getCount()); 
                Thread.sleep(10);
            }
        } catch (Exception e){}
    }
}
