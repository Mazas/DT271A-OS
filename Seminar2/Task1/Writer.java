/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readerwriter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jof
 */
//a writer updates the content of a message
//in the run method 10 writings are performed before exit
//each writer gets an id which can be used to track the running of individual threads
public class Writer implements Runnable{
    private int id;
    private Data myData;
    
    public Writer(int nID,Data inData){
        id=nID;
        myData=inData;
    }
    
    @Override
    public void run(){
        for(int i=1;i<5;i++){
            try {
                Thread.sleep((int)(Math.random()*100));
                myData.write(id,i);       
            } catch (InterruptedException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
