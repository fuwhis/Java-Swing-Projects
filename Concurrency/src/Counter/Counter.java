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
public class Counter {
    int count = 0; 
    
    public synchronized void increase(){
        count++; 
    }

    public synchronized int getCount() {
        return count;
    }
    
    
}
