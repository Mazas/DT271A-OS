
package producerconsumer;


//the consumer will read 10 values from the buffer 
//calculate their sum, print it and then exit
public class Consumer implements Runnable{
    
    private Buffer myBuffer;
    
    public Consumer(Buffer buffer){
        myBuffer=buffer;    
    }
    
    @Override
    public void run(){
        int sum=0;
        try {
            for(int i=0;i<10;i++){
                //first sleep
                //the purpose of the sleep is to simulate some work that 
                //might be done in a real application
                //with the sleep we also force context switching
                Thread.sleep((int)(Math.random()*100));
                sum+=myBuffer.read();

                //second sleep
                //the purpose of the sleep is to simulate some work that 
                //might be done in a real application
                //with the sleep we also force context switching
                //The reason to force context switching is to make the race condition visible
                //otherwise we might run the application several times without
                //noticing that we have a serious flaw.
                Thread.sleep((int)(Math.random()*100));
            }
            System.out.println("\nThe sum was "+ sum);
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interupted");
        }
        finally {
        }
    }
}
