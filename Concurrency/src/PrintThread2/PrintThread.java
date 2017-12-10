package PrintThread2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quy Nguyen
 */
public class PrintThread extends Thread {
    PrintData print; 
    public PrintThread(PrintData print){
        this.print = print; 
    }
    @Override
    public void run(){
        this.print.print();
    }
}
