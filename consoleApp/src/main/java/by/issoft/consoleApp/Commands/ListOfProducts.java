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
        if (input.equals("products")){
            System.out.println("-------------------------------------------------------------------");
            System.out.println("                          LIST OF PRODUCTS");
            System.out.println("-------------------------------------------------------------------");
            List<Product> allProducts = Store.getInstance().getAllProducts();
            for(Product product : allProducts){
                System.out.println(product);
            }
            System.out.println("-------------------------------------------------------------------");
        }
        else if(getNext() == null){
            System.out.println("Wrong command");
        }
    }
}
