package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public class PhoneCategory extends Category {
    public PhoneCategory(String name, List<Product> productList) {
        super(name, productList);
    }
}
