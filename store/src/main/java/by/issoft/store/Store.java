package by.issoft.store;

import by.issoft.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static List<Category> categoryList = new ArrayList<>();
    public static void addCategoryToList(Category category) {
        categoryList.add(category);
    }
    public void printCategoriesAndProducts(){
        for (Category category : categoryList) {
            category.printProductsFromList();
        }
    }
}
