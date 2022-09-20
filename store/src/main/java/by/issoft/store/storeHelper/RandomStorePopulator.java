package by.issoft.store.storeHelper;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.Random;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void generateRandomProducts() {
        RandomProductGenerator products = new RandomProductGenerator();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            Product product = new Product(
                    products.generateProductName(),
                    products.generateProductPrice(),
                    products.generateProductRate()
            );
            System.out.println("Book title: " + product.getName() + "; Rate: " + product.getRate() + "; Price: " + product.getPrice());
        }
    }
}
