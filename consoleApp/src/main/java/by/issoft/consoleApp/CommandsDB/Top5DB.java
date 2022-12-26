package by.issoft.consoleApp.CommandsDB;

import by.issoft.store.BD.DataBaseHandler;

import java.sql.SQLException;

public class Top5DB extends CommandDB{
    public Top5DB() {
        super(new CreateOrderDB());
    }
    @Override
    public void doCommandDB(String input) throws SQLException {
        if (input.equals("top5")){
            DataBaseHandler.Top5();
        }
        else if (getNext() != null) {
            getNext().doCommandDB(input);
        }
    }
}
