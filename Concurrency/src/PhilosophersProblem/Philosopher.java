/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhilosophersProblem;

/**
 *
 * @author Quy Nguyen
 */
public class Philosopher extends Thread{
    ChopStick leftStick, rightStick; 
    int position; 

    public Philosopher(int pos, ChopStick lStick, ChopStick rStick) {
        position=pos; leftStick=lStick; rightStick=rStick;
    }
    public void eat(){
        leftStick.getUp();  rightStick.getUp();
        System.out.println("The " + position + "(th) philosopher is eating");
    }
    public void think()
    {
        leftStick.getDown();   rightStick.getDown();
        System.out.println("The " + position + "(th) philosopher is thinking\"");
    }
    public void run(){
        while (true)
        {
            eat();
            try { sleep(1000); }
            catch (InterruptedException e)
            {
                System.out.println("An error occured!");
            }
            think();
            try{  sleep(1000);  }
            catch (InterruptedException e){
                System.out.println("An error occured!");
            }
        }
    }
}
