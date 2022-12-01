package by.issoft.store.multithreading;

import java.util.concurrent.TimeUnit;

public class CleanOrder extends Thread{

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        CreateOrder.getOrderedProducts().clear();
            System.out.println("The list of orders has been cleared");
        }
    }
}
