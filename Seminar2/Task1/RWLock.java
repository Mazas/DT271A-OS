//implementation of a reader writer lock
//a reader use the following methods
//acquireRead and releaseRead
//a writer use the following methods
//acquireWrite and releaseWrite
package readerwriter;

/**
 *
 * @author jof
 */
public class RWLock {

    private boolean reading, writing;
    public RWLock(){
        reading =false;
        writing = false;
    }
    
    public synchronized void acquireRead(){
        try {
            while (reading || writing) {
                wait();
            }
            reading=true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public synchronized void acquireWrite(){
        try{
            while(reading||writing){
                wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public synchronized void releaseRead(){
        try{
            reading=false;
            notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public synchronized void releaseWrite() {
        try{
            writing=false;
            notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
