
package producerconsumer;

import java.util.concurrent.Semaphore;

public class Buffer {
    //representing a buffer that can store Length number of integers
    private int[] buffer; 
    private int Length; 
    //readPos points to the next position that we should read a value from
    //writePos points to the new position that we should write a value to
    private int readPos;
    private int writePos;

    private int occupied;

    private Semaphore semaphore;
    
    public Buffer(int size){
        occupied=0;
        semaphore= new Semaphore(1);
        //create an empty buffer that can store size integers
        Length=size;
        buffer=new int[Length];
        //in an empty buffer readPos=writePos=0;
        readPos=0;
        writePos=0;
    }
    
    //insert a value in the buffer
    public void insert(int value){
        try {
            semaphore.acquire();
            //store value
            while (occupied == Length) {

            }
            buffer[writePos] = value;
            occupied++;
            //update write position
            writePos++;
            if (writePos == Length) {
                writePos = 0;
            }
            semaphore.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //remove a value from the buffer
    public int read(){
        int temp=0;
        try {
            while (occupied == 0) {
                //spin
                temp=0;
            }
            semaphore.tryAcquire();
            occupied--;
            semaphore.release();
            //first store value to read in temp
            temp = buffer[readPos];
            //update readPos and then return value
            readPos++;
            if (readPos == Length)
                readPos = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp;
    }
}
