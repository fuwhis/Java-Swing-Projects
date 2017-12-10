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
public class Output {
    
    //Main thread
    public static void main(String[] args){
        int intArr[] = new int[10];
        PrintThread printers[] = new PrintThread[10]; 
        
        PrintData printData = new PrintData(); 
        
        for (int i = 0; i < 10; i++){
            printers[i] = new PrintThread(printData); 
        }
    }
}
