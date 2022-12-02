package by.issoft.consoleApp.Commands;

import by.issoft.store.multithreading.CreateOrder;

public class CreateOrders extends Command{
    public CreateOrders() {
        super(new ListOfProducts());
    }
    @Override
    public void doCommand(String input) {
        if (input.equals("order")) {
            CreateOrder order = new CreateOrder();
            order.start();
        }
        else if (getNext() != null) {
            getNext().doCommand(input);
        }
    }
}
