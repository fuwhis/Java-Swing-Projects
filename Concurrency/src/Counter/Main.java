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
public class Main {
    public static void main(String[] args){
        Counter ct = new Counter(); 
        
        Threads th[] = new Threads[10]; 
        for (int i = 0; i < 10; i++){
            th[i] = new Threads(ct);
        }
        try{
        for (int i = 0; i < 10; i++){
            th[i].start();
            Thread.sleep(200);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       // System.out.println("Main Count = " + ct.getCount());
    }
}
