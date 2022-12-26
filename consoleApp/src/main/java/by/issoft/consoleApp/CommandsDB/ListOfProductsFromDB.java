package by.issoft.consoleApp.CommandsDB;

import by.issoft.store.BD.DataBaseHandler;

import java.sql.SQLException;

public class ListOfProductsFromDB extends CommandDB{
    public ListOfProductsFromDB() {
        super(new SortedListOfProductsDB());
    }
    @Override
    public void doCommandDB(String input) throws SQLException {
        if (input.equals("products")){
            DataBaseHandler.listOfProducts();
        }
        else if (getNext() != null) {
            getNext().doCommandDB(input);
        }
    }
}
