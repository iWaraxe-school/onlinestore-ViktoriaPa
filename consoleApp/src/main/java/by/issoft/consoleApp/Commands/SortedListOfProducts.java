package by.issoft.consoleApp.Commands;

import by.issoft.store.Store;
import by.issoft.store.comparator.ProductComparator;

public class SortedListOfProducts extends Command {
    public SortedListOfProducts() {
        super(new Quit());
    }

    @Override
    public void doCommand(String input) {
        if (input.equals("sorted")) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("                       SORTED LIST OF PRODUCTS");
            System.out.println("-------------------------------------------------------------------");
            ProductComparator productComparator = new ProductComparator(Store.getInstance());
            productComparator.sortProducts();
            System.out.println("-------------------------------------------------------------------");
        }
        else if (getNext() != null) {
            getNext().doCommand(input);
        }
    }
}
