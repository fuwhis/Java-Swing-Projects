/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Quy Nguyen
 */
public class PenService extends UnicastRemoteObject implements PenMngInterface{
    String fileName;

    public PenService(String filename) throws RemoteException {
        super();
        this.fileName = filename;
    }
    
    

    @Override
    public Vector getData() throws RemoteException {
        Vector data = new Vector(0);
        try {
            FileReader f = new FileReader(fileName);
            BufferedReader br = new BufferedReader(f);
            String line;
            StringTokenizer st;

            //code, manufacturer, price, model, size
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                Vector v = new Vector();
                v.add(st.nextToken().trim());
                v.add(st.nextToken().trim());
                v.add(Integer.parseInt(st.nextToken().trim()));
                v.add(st.nextToken().trim());
                v.add(Integer.parseInt(st.nextToken().trim()));
                data.add(v);
            }
            br.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean saveData(Vector data) throws RemoteException {
        try {
            FileWriter f = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(f);
            for (int i = 0; i < data.size(); i++) {
                Vector v = ((Vector) (data.get(i)));
                String s = "";
                s += v.get(0) + "," + v.get(1) + "," + v.get(2) + "," + v.get(3) + "," + v.get(4);
                pw.println(s);
            }
            pw.close();
            f.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
