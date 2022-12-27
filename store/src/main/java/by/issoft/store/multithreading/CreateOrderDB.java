package by.issoft.store.multithreading;

import by.issoft.domain.Product;
import by.issoft.store.BD.DataBaseHandler;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrderDB extends Thread {

    @Override
    public void run() {
        Product randomProduct;
        try {
            randomProduct = DataBaseHandler.getRandomProduct();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ordered product: " + randomProduct);

        try {
            DataBaseHandler.addOrderToDB(randomProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            DataBaseHandler.getOrdersFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(30));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " finished execution");
    }
}
