package by.issoft.consoleApp.Commands;

import by.issoft.store.Store;
import by.issoft.store.comparator.ProductComparator;

public class SortedListOfProducts extends Command {
    public SortedListOfProducts() {
        super(new ListOfProducts());
    }

    @Override
    public void doCommand(String input) {
        if (input.equals("SortedListOfProducts")) {
            ProductComparator productComparator = new ProductComparator(Store.getInstance());
            productComparator.sortProducts();
        }
        else if (getNext() != null) {
            getNext().doCommand(input);
        }
    }
}
