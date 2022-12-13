package by.issoft.consoleApp;
import by.issoft.consoleApp.BD.DataBaseHandler;
import by.issoft.consoleApp.Commands.Top5;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.multithreading.CleanOrder;
import by.issoft.store.storeHelper.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static by.issoft.consoleApp.BD.ConfigsTestStore.*;

public class StoreApp {
    public static void main(String[] args) {
        Store storeWithCategoriesAndProducts = Store.getInstance();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
        populator.generateRandomStore();
        storeWithCategoriesAndProducts.printCategoriesAndProducts();

        CleanOrder cleanOrder = new CleanOrder();
        cleanOrder.start();

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

            DataBaseHandler.clearTable(dbConnection);

            List<Product> allProducts = Store.getInstance().getAllProducts();
            for(Product product : allProducts){
                DataBaseHandler.addProductToDB(dbConnection, product);
            }

            DataBaseHandler.printProucts(dbConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("-------------------------------------------------------------------");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter the command (products, top5, sorted, order, quit): ");
                String input = bufferedReader.readLine();
                new Top5().doCommand(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
