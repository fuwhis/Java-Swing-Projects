/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yield;
import java.lang.*;
/**
 *
 * @author Quy Nguyen
 */
public class YieldTest extends Thread{
    
    public void run(){
        for(int i=0; i<10; i++)
            System.out.println(Thread.currentThread().getName() + " in control");
    }
}
