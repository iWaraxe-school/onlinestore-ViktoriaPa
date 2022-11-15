package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static Store store;
    private List<Category> categoryList = new ArrayList<>();
    private Store() {
    }
    public static Store getStore(){
        if(store == null){
            store = new Store();
        }
        return store;
    }
    public void addCategoryToList(Category category) {
        categoryList.add(category);
    }
    public void printCategoriesAndProducts(){
        for (Category category : categoryList) {
            category.printProductsFromList();
        }
    }
    public List<Product> getAllProducts() {
        List<Product> listOfAllProducts = new ArrayList<>();
        for (Category category : categoryList){
            List<Product> productList = category.getProductList();
            listOfAllProducts.addAll(productList);
        }
        return listOfAllProducts;
    }
}
