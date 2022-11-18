package by.issoft.consoleApp.Commands;

public abstract class Command {
    private Command next;

    public Command(Command next) {
        this.next = next;
    }

    public Command getNext() {
        return next;
    }

    public abstract void doCommand(String input);
}
