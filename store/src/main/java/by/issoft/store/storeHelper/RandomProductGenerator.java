package by.issoft.store.storeHelper;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    Faker faker = new Faker();
    public String generateProductName() {
        return faker.book().title();
    }

    public Double generateProductPrice() {
        return faker.number().randomDouble(2, 0, 1000);
    }

    public Double generateProductRate() {
        return faker.number().randomDouble(1, 0, 10);
    }
}

