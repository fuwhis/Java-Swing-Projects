/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yield;

/**
 *
 * @author Quy Nguyen
 */
public class yieldDemo{
        public static void main(String[] args){
            YieldTest yt = new YieldTest();
            yt.start();
            
            for(int i=0; i <10; i++){
                Thread.yield();
                
                System.out.println(Thread.currentThread().getName() + " in control");
            }
        }
    }
