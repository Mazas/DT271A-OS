//implementation of a reader writer lock
//a reader use the following methods
//acquireRead and releaseRead
//a writer use the following methods
//acquireWrite and releaseWrite
package readerwriter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author jof
 */
public class RWLock {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private Lock rlock;
    private Lock wlock;


    public RWLock(){
        rlock = lock.readLock();
        wlock = lock.writeLock();
    }
    
    public void acquireRead(){
        try{
            rlock.lock();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void acquireWrite(){
        try{
            wlock.lock();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void releaseRead(){
        rlock.unlock();
    }
    
    public void releaseWrite() {
        wlock.unlock();
    }
}
