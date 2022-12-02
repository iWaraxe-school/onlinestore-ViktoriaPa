package by.issoft.store.multithreading;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CreateOrder extends Thread{
    private static List<Product> orderedProducts = new CopyOnWriteArrayList<>();
    private Store store = Store.getInstance();
    public static List<Product> getOrderedProducts() {
        return orderedProducts;
    }
    @Override
    public void run(){
        Product randomProduct = store.getRandomProduct();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ordered product: " + randomProduct);
        orderedProducts.add(randomProduct);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("                        LIST OF ORDERS");
        System.out.println("-------------------------------------------------------------------");
        int index = 0;
        for (Product product : orderedProducts){
            index++;
            System.out.print(index + ": ");
            System.out.println(product);
        }
        System.out.println("-------------------------------------------------------------------");

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(30));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " finished execution");
    }
}
