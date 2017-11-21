
package producerconsumer;


public class ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // The purpose of this little program is to illustrate the 
        // producer/consumer problem (also called bounded buffer)
        
        System.out.println("Creating a buffer of size 3");
        Buffer myBuffer=new Buffer(3);
        //create a producer
        Thread producer=new Thread(new Producer(myBuffer));
        producer.start();
        //create a consumer
        Thread consumer=new Thread(new Consumer(myBuffer));
        consumer.start();
    }
    
}
