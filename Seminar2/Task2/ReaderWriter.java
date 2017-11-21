/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readerwriter;

/**
 *
 * @author jof
 */
public class ReaderWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data data=new Data();
        //create writers
        Thread rt;
        rt=new Thread(new Writer(0,data));
        rt.start();
        rt=new Thread(new Writer(1,data));
        rt.start();
        //create readers
        Thread th;
        for(int r=0;r<5;r++){
            th=new Thread(new Reader(r,data));
            th.start();
        }
    }
    
}
