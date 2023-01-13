package by.issoft.consoleApp;
import by.issoft.store.BD.DataBaseHandler;
import by.issoft.store.http.client.Client;
import by.issoft.store.http.server.Server;

import java.io.IOException;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws IOException, SQLException {
        DataBaseHandler.fillStoreDB();
        Server.startServer();

        Client.clientMakesOrder();

//        //Store From DB
//        DataBaseHandler.fillStoreDB();
//
//        CleanOrderDB cleanOrder = new CleanOrderDB();
//        cleanOrder.start();
//
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            while (true) {
//                System.out.println("-------------------------------------------------------------------");
//                System.out.println("Enter the command (products, sorted, top5, order, quit): ");
//                String input = bufferedReader.readLine();
//                new ListOfProductsFromDB().doCommandDB(input);
//            }
//        } catch (IOException | SQLException e) {
//            throw new RuntimeException(e);
//        }

//        //Old Store
//        Store storeWithCategoriesAndProducts = Store.getInstance();
//        RandomStorePopulator populator = new RandomStorePopulator(storeWithCategoriesAndProducts);
//        populator.generateRandomStore();
//        storeWithCategoriesAndProducts.printCategoriesAndProducts();
//
//        CleanOrder cleanOrder = new CleanOrder();
//        cleanOrder.start();
//
//        System.out.println("-------------------------------------------------------------------");
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            while (true) {
//                System.out.println("Enter the command (products, top5, sorted, order, quit): ");
//                String input = bufferedReader.readLine();
//                new Top5().doCommand(input);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
