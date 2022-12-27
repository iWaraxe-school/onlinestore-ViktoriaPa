package by.issoft.consoleApp.CommandsDB;

import by.issoft.store.multithreading.CreateOrder;

import java.sql.SQLException;

public class CreateOrderDB extends CommandDB{

    public CreateOrderDB() {
        super(new QuitDB());
    }
    @Override
    public void doCommandDB(String input) throws SQLException {
        if (input.equals("order")){
            by.issoft.store.multithreading.CreateOrderDB order = new by.issoft.store.multithreading.CreateOrderDB();
            order.start();
        }
        else if (getNext() != null) {
            getNext().doCommandDB(input);
        }
    }
}
