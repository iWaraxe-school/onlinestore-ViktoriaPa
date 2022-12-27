package by.issoft.consoleApp.CommandsDB;

import java.sql.SQLException;

public abstract class CommandDB {
    private CommandDB next;

    public CommandDB(CommandDB next) {
        this.next = next;
    }

    public CommandDB getNext() {
        return next;
    }

    public abstract void doCommandDB(String input) throws SQLException;
}
