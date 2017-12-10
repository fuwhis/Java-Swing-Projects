/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Join;

/**
 *
 * @author Quy Nguyen
 */
public class JoinTest {
    public static void main(String[] args){
        
        System.out.println("\n==> Main thread starting...\n");
        
        Thread joinThreadA = new JoinThread("A*", 2);
        Thread joinThreadB = new JoinThread("B*", 3);
        
        Thread noJoinThreadC = new JoinThread("C", 5);
        
        try{
        joinThreadA.start();
        joinThreadB.start();
        noJoinThreadC.start();
        //Using join() method
        joinThreadA.join();
        joinThreadB.join();
        }
        catch(InterruptedException e)
        {
            System.out.println("Hello from main thread...");
        
            System.out.println("Thread A isLive? " + joinThreadA.isAlive());
            System.out.println("Thread B isLive? " + joinThreadB.isAlive());
            System.out.println("Thread C isLive? " + noJoinThreadC.isAlive());
            
            System.out.println("\n==> Main thread end!\n");
        }   
    }
}
