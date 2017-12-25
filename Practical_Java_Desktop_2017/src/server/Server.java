/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.PenService;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Quy Nguyen
 */
public class Server {
    public static void main(String[] args) {
        String serviceName = "rmi://localhost:12340/PenService";
        String fileName = "pen.txt";
        try {
            PenService e = new PenService(fileName);
            LocateRegistry.createRegistry(12340);
            Naming.rebind(serviceName, e);
            System.out.println("Service " + serviceName + " is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
