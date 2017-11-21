/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seminar4a;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Seminar4a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String filename="Seminar4.a";
       createFile(filename);
       //uncomment later on when we shoudl read the file
       readFile(filename);
        
    }
    
    //creates a file and write the bytes 255,254,..,0 to it
    public static void createFile(String filename){
         java.io.FileOutputStream outFile = null;
        try {
            // create byte array
            byte data[];
            data = new byte[256];
            for(int i=0;i<256;i++){
                data[i]=(byte)(255-i);
            }   
            //create FileOutputStream
            outFile = new java.io.FileOutputStream(filename);
            //write byte to outputstream
            for(int i=0;i<256;i++)
                outFile.write(data[i]);
            //close file
            outFile.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    //open a file with the name filename and read and prints the bytes from the file
    public static void readFile(String filename){
//         java.io.FileInputStream inFile = null;
//        try {
//            //create FileInputStream
//            inFile = new java.io.FileInputStream(filename);
//            //read byte from file
//            //print byte
//            int data=inFile.read();
//            while(data!=-1){
//                System.out.printf("%02x ", data);
//                System.out.println((byte) data);
//                data=inFile.read();
//            }
//            //close file
//            inFile.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                inFile.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Seminar4a.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        RandomAccessFile in = null;
        try {
            in = new RandomAccessFile(filename,"r");
            short[] pos = { 12, 5, 167, 200};
            for(short i: pos){
                in.seek(i);
                System.out.println(in.readByte());
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
