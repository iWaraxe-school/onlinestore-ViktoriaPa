package by.issoft.consoleApp;
import by.issoft.consoleApp.Commands.Top5;
import by.issoft.store.Store;
import by.issoft.store.multithreading.CleanOrder;
import by.issoft.store.storeHelper.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) {
        Store storeWithCategoriesAndProducts = Store.getInstance();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
        populator.generateRandomStore();
        storeWithCategoriesAndProducts.printCategoriesAndProducts();

        CleanOrder cleanOrder = new CleanOrder();
        cleanOrder.start();

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
