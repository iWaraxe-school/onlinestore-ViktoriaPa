package by.issoft.consoleApp.Commands;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.List;

public class ListOfProducts extends Command {

    public ListOfProducts() {
        super(null);
    }

    @Override
    public void doCommand(String input) {
        if (input.equals("ListOfProducts")){
            List<Product> allProducts = Store.getInstance().getAllProducts();
            for(Product product : allProducts){
                System.out.println(product);
            }
        }
        else if(getNext() == null){
            System.out.println("Wrong command");
        }
    }
}
