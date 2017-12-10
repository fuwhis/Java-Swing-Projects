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
public class Main {
    
    // Main Thread
    public static void main(String[] args){
        PrintThread pt = new PrintThread(); 
        //start a new thread 
        // Asynchonous
        // Type: non-deamon Thread (~Window Process/Foreground Process)
        pt.setDaemon(true);
        pt.start(); //=> run
        
        try{
            System.out.println("MainThread's id: " + Thread.currentThread().getId());
            for (int i = 0; i < 10; i++)
            {
                System.out.println("Main: " + i);
                Thread.sleep(500); //~1s
            }
        }
        catch (Exception e){                 
        }
    }
}
