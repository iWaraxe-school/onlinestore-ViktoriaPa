package by.issoft.consoleApp.Commands;

import by.issoft.store.Store;
import by.issoft.store.comparator.ProductComparator;

public class Top5 extends Command {
    public Top5() {
        super(new SortedListOfProducts());
    }

    @Override
    public void doCommand(String input) {
        if (input.equals("Top5")) {
            ProductComparator productComparator = new ProductComparator(Store.getInstance());
            productComparator.top5();
        }
        else if (getNext() != null) {
            getNext().doCommand(input);
        }
    }
}