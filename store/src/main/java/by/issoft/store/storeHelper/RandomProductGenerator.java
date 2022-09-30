package by.issoft.store.storeHelper;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    Faker faker = new Faker();
    public String generateProductName(String subCategoryName) {
        switch (subCategoryName) {
            case "Book":
                return faker.book().title();
            case "Color":
                return faker.color().name();
            case "Food":
                return faker.food().dish();
        }
        return subCategoryName;
    }

    public Double generateProductPrice() {
        return faker.number().randomDouble(2, 0, 1000);
    }

    public Double generateProductRate() {
        return faker.number().randomDouble(1, 0, 10);
    }
}

