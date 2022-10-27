package by.issoft.consoleApp;
import by.issoft.store.comparator.ProductComparator;
import by.issoft.store.Store;
import by.issoft.store.storeHelper.RandomStorePopulator;

public class StoreApp {
    public static void main(String[] args) {
//        XMLParser parser = new XMLParser();
//        Map<String, String> typesOfSorting = parser.parse();
//        System.out.println(typesOfSorting);

        Store storeWithCategoriesAndProducts = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
        populator.generateRandomStore();
        storeWithCategoriesAndProducts.printCategoriesAndProducts();

        ProductComparator productComparator = new ProductComparator(storeWithCategoriesAndProducts);
        productComparator.sortProducts();
        productComparator.top5();

//        System.out.println("-----------------------------");
//        System.out.println("   ListOFAllProducts");
//        System.out.println("-----------------------------");
//        List<Product> allProducts = storeWithCategoriesAndProducts.getAllProducts();
//        for(Product product : allProducts){
//            System.out.println(product);
//        }
//
//        System.out.println("-----------------------------");
//        System.out.println("   SortedListOFAllProductsSimple");
//        System.out.println("-----------------------------");
//        Comparator<Product> nameComparatorSimple = Comparator.comparing(Product::getName, String::compareToIgnoreCase);
//        Comparator<Product> priceComparatorSimple = Comparator.comparing((Product::getPrice));
//        Comparator<Product> rateComparatorSimple = Comparator.comparing((Product::getRate));
//
//        Comparator<Product> generalComparatorSimple = nameComparatorSimple
//                .thenComparing(priceComparatorSimple.reversed())
//                .thenComparing(rateComparatorSimple.reversed());
//
//        allProducts.sort(generalComparatorSimple);
//        for(Product product :allProducts){
//            System.out.println(product);
//        }
    }
}
