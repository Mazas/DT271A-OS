
package producerconsumer;



//the producer will generate the number 1, 2, 3, ..., 10 
//and then write them to a buffer

public class Producer implements Runnable{
    private Buffer myBuffer;
    
    public Producer(Buffer buffer){
        myBuffer=buffer;
    }
    
    @Override
    public void run(){
        try {
            for(int i=1;i<=10;i++){
                //first sleep
                //the purpose of the sleep is to simulate some work that 
                //might be done in a real application
                //with the sleep we also force context switching
                Thread.sleep((int)(Math.random()*100));
                myBuffer.insert(i);
                //second sleep
                //the purpose of the sleep is to simulate some work that 
                //might be done in a real application
                //with the sleep we also force context switching
                //The reason to force context switching is to make the race condition visible
                //otherwise we might run the application several times without
                //noticing that we have a serious flaw.
                Thread.sleep((int)(Math.random()*100));
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interupted");
        }
        finally {
        }
    }
    

}
