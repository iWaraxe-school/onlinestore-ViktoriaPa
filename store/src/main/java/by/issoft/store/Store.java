package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {
    private static Store store;
    private List<Category> categoryList = new ArrayList<>();
    private Store() {
    }
    public static Store getInstance(){
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
    public Product getRandomProduct(){
        List<Product> listOfAllProducts = new ArrayList<>();
        for (Category category : categoryList){
            List<Product> productList = category.getProductList();
            listOfAllProducts.addAll(productList);
        }

        Random rand = new Random();
        return listOfAllProducts.get(rand.nextInt(listOfAllProducts.size()));
    }
}
