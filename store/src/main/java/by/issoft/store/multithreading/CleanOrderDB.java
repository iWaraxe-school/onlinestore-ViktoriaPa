package by.issoft.store.multithreading;

import by.issoft.store.BD.DataBaseHandler;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CleanOrderDB extends Thread{
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                DataBaseHandler.clearOrdersInDB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The list of orders has been cleared");
        }
    }
}
