package by.issoft.consoleApp.CommandsDB;

import by.issoft.store.BD.DataBaseHandler;

import java.sql.SQLException;

public class SortedListOfProductsDB extends CommandDB{
    public SortedListOfProductsDB() {
        super(new Top5DB());
    }
    @Override
    public void doCommandDB(String input) throws SQLException {
        if (input.equals("sorted")){
            DataBaseHandler.sortedProducts();
        }
        else if (getNext() != null) {
            getNext().doCommandDB(input);
        }
    }
}
