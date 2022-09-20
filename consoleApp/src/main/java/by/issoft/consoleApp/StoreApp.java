package by.issoft.consoleApp;
import by.issoft.store.Store;
import by.issoft.store.storeHelper.RandomStorePopulator;

public class StoreApp {
    public static void main(String[] args) {
        Store storeWithProducts = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithProducts);
        populator.generateRandomProducts();
    }
}