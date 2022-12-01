package by.issoft.consoleApp.Commands;

public class Quit extends Command {
    public Quit() {
        super(new CreateOrders());
    }
    @Override
    public void doCommand(String input) {
        if (input.equals("quit")) {
            System.out.println("Exit!");
            System.exit(0);
        }
        else if (getNext() != null) {
            getNext().doCommand(input);
        }
    }
}
