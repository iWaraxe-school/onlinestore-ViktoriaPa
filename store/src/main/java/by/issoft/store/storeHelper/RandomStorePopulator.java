package by.issoft.store.storeHelper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;


public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void generateRandomStore() {

        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Category.class).asClass());
        Set<Category> subCategorySet = new HashSet<>();
        RandomProductGenerator products = new RandomProductGenerator();
        for (Class<?> type : subTypes){
            Category category = null;
            try {
                category = (Category) type.getConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            subCategorySet.add(category);
        }
        for (Category subCategory : subCategorySet) {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10); i++) {
                Product product = new Product(
                        products.generateProductName(subCategory.getName()),
                        products.generateProductPrice(),
                        products.generateProductRate()
                );
                subCategory.addProductToList(product);
            }
            store.addCategoryToList(subCategory);
        }

    }
}
