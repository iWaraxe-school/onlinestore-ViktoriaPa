package by.issoft.consoleApp;
import by.issoft.domain.XMLparser.XMLParser;
import by.issoft.store.Store;
import by.issoft.store.storeHelper.RandomStorePopulator;

import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        XMLParser parser = new XMLParser();
        Map<String, String> typesOfSorting = parser.parse();
        System.out.println(typesOfSorting);

        Store storeWithCategoriesAndProducts = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
        populator.generateRandomStore();
        storeWithCategoriesAndProducts.printCategoriesAndProducts();
    }
}
