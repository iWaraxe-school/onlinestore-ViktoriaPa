package by.issoft.consoleApp;
import by.issoft.domain.Product;
import by.issoft.store.comparator.ProductComparator;
import by.issoft.store.Store;
import by.issoft.store.storeHelper.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) {
        Store storeWithCategoriesAndProducts = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
        populator.generateRandomStore();
        storeWithCategoriesAndProducts.printCategoriesAndProducts();

        System.out.println("-------------------------------------------------------------------");

        BufferedReader bufferedReader = null;
        ProductComparator productComparator = new ProductComparator(storeWithCategoriesAndProducts);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                System.out.print("Enter the command: ");
                String input = bufferedReader.readLine();
                switch (input) {
                    case "top5":
                        productComparator.top5();
                        break;
                    case "ListOfProducts":
                        List<Product> allProducts = storeWithCategoriesAndProducts.getAllProducts();
                        for(Product product : allProducts){
                            System.out.println(product);
                        }
                        break;
                    case "SortedListOfProducts":
                        productComparator.sortProducts();
                        break;
                    case "quit":
                        System.out.println("Exit!");
                        System.exit(0);
                    default:
                        System.out.println("Wrong command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
