package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    private List<Product> productList = new ArrayList<Product>();

    public Category(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
