package by.issoft.consoleApp.CommandsDB;

import java.sql.SQLException;

public class QuitDB extends CommandDB{
    public QuitDB() {
        super(null);
    }
    @Override
    public void doCommandDB(String input) throws SQLException {
        if (input.equals("quit")) {
            System.out.println("Exit!");
            System.exit(0);
        }
        else if(getNext() == null){
            System.out.println("Wrong command");
        }
    }
}
