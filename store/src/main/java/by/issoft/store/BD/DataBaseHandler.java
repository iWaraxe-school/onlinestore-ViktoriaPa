package by.issoft.store.BD;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.storeHelper.RandomProductGenerator;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static by.issoft.store.BD.ConfigsTestStore.*;
import static org.reflections.scanners.Scanners.SubTypes;

public class DataBaseHandler {
    static Connection DBCONNECTION = null;
    public static void fillStoreDB (){
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBCONNECTION = DriverManager.getConnection(connectionString, dbUser, dbPass);

            DataBaseHandler.dropTables();
            DataBaseHandler.createTables();
            DataBaseHandler.fillDB();
            DataBaseHandler.productsWithCategories();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void fillDB () throws SQLException {
        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Category.class).asClass());
        Set<Category> subCategorySet = new HashSet<>();
        for (Class<?> type : subTypes) {
            Category category = null;
            try {
                category = (Category) type.getConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            subCategorySet.add(category);
        }
        for (Category category : subCategorySet) {
            String addCategory = String.format("INSERT INTO CATEGORIES(NAME) VALUES(?);");
            PreparedStatement preparedStatementAddCategory = DBCONNECTION.prepareStatement(addCategory);
            preparedStatementAddCategory.setString(1, category.getName());
            preparedStatementAddCategory.executeUpdate();

            String idBookCategory = String.format("SELECT ID FROM CATEGORIES WHERE NAME = '%s'", category.getName());
            PreparedStatement preparedStatementIdCategory = DBCONNECTION.prepareStatement(idBookCategory);
            ResultSet resultSet = preparedStatementIdCategory.executeQuery(idBookCategory);

            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("ID");
            }

            RandomProductGenerator products = new RandomProductGenerator();
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10); i++) {
                String addProduct = String.format("INSERT INTO PRODUCTS(CATEGORY_ID, NAME, PRICE, RATE) VALUES(?, ?, ?, ?);");
                PreparedStatement preparedStatementAddProduct = DBCONNECTION.prepareStatement(addProduct);
                preparedStatementAddProduct.setInt(1, id);
                preparedStatementAddProduct.setString(2, products.generateProductName(category.getName()));
                preparedStatementAddProduct.setDouble(3, products.generateProductPrice());
                preparedStatementAddProduct.setDouble(4, products.generateProductRate());
                preparedStatementAddProduct.executeUpdate();
            }
        }
    }
    public static void dropTables () throws SQLException {
        String dropTableProducts = String.format("DROP TABLE IF EXISTS PRODUCTS;");
        PreparedStatement preparedStatementDropTableProducts = DBCONNECTION.prepareStatement(dropTableProducts);
        preparedStatementDropTableProducts.executeUpdate();

        String dropTableCategories = String.format("DROP TABLE IF EXISTS CATEGORIES;");
        PreparedStatement preparedStatementDropTableCategories = DBCONNECTION.prepareStatement(dropTableCategories);
        preparedStatementDropTableCategories.executeUpdate();

        String dropTableOrders = String.format("DROP TABLE IF EXISTS ORDERS;");
        PreparedStatement preparedStatementDropTableOrders = DBCONNECTION.prepareStatement(dropTableOrders);
        preparedStatementDropTableOrders.executeUpdate();
    }
    public static void createTables () throws SQLException {
        String createTableCategories = String.format("CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "NAME VARCHAR(255) NOT NULL);");
        PreparedStatement preparedStatementCreateTableCategories = DBCONNECTION.prepareStatement(createTableCategories);
        preparedStatementCreateTableCategories.executeUpdate();

        String createTableProducts = String.format("CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL," +
                "NAME VARCHAR(45) NOT NULL," +
                "PRICE DOUBLE NOT NULL," +
                "RATE DOUBLE NOT NULL," +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));");
        PreparedStatement preparedStatementCreateTableProducts = DBCONNECTION.prepareStatement(createTableProducts);
        preparedStatementCreateTableProducts.executeUpdate();

        String createTableOrders = String.format("CREATE TABLE IF NOT EXISTS ORDERS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "NAME VARCHAR(255) NOT NULL, " +
                "PRICE DOUBLE NOT NULL, " +
                "RATE DOUBLE NOT NULL);");
        PreparedStatement preparedStatementCreateTableOrders = DBCONNECTION.prepareStatement(createTableOrders);
        preparedStatementCreateTableOrders.executeUpdate();
    }
    public static void listOfProducts() throws SQLException {
        String printFullListOfProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printFullListOfProducts);
        ResultSet listOfProducts = preparedStatement.executeQuery(printFullListOfProducts);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                FULL LIST OF PRODUCTS FROM DATA BASE");
        System.out.println("-------------------------------------------------------------------");
        while (listOfProducts.next()){
            Product product = new Product();
            product.setName(listOfProducts.getString(1));
            product.setPrice(listOfProducts.getDouble(2));
            product.setRate(listOfProducts.getDouble(3));
            System.out.println(product);
        }
    }
    public static void productsWithCategories() throws SQLException {
        String printCategories = String.format("SELECT ID, NAME FROM CATEGORIES");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printCategories);
        ResultSet setOfCategories = preparedStatement.executeQuery(printCategories);
        while (setOfCategories.next()){
            String nameOfCategory = setOfCategories.getString(2);
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Category: " + nameOfCategory);

            String printProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS WHERE CATEGORY_ID = %s", setOfCategories.getString(1));
            PreparedStatement preparedStatement1 = DBCONNECTION.prepareStatement(printProducts);
            ResultSet setOfProducts = preparedStatement1.executeQuery(printProducts);
            while (setOfProducts.next()){
                Product product = new Product();
                product.setName(setOfProducts.getString(1));
                product.setPrice(setOfProducts.getDouble(2));
                product.setRate(setOfProducts.getDouble(3));
                System.out.println(product);
            }
        }
    }
    public static void sortedProducts() throws SQLException {
        String printSortedListOfProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY NAME ASC");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printSortedListOfProducts);
        ResultSet listOfProducts = preparedStatement.executeQuery(printSortedListOfProducts);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("              SORTED LIST OF PRODUCTS FROM DATA BASE");
        System.out.println("-------------------------------------------------------------------");
        while (listOfProducts.next()){
            Product product = new Product();
            product.setName(listOfProducts.getString(1));
            product.setPrice(listOfProducts.getDouble(2));
            product.setRate(listOfProducts.getDouble(3));
            System.out.println(product);
        }
    }
    public static void Top5() throws SQLException {
        String printSortedListOfProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY PRICE DESC LIMIT 5");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printSortedListOfProducts);
        ResultSet listOfProducts = preparedStatement.executeQuery(printSortedListOfProducts);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                              Top 5");
        System.out.println("-------------------------------------------------------------------");
        while (listOfProducts.next()){
            Product product = new Product();
            product.setName(listOfProducts.getString(1));
            product.setPrice(listOfProducts.getDouble(2));
            product.setRate(listOfProducts.getDouble(3));
            System.out.println(product);
        }
    }
    public static Product getRandomProduct() throws SQLException {
        String printSortedListOfProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY RAND () LIMIT 1");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printSortedListOfProducts);
        ResultSet listOfProducts = preparedStatement.executeQuery(printSortedListOfProducts);

        Product product = new Product();
        while (listOfProducts.next()){
            Product productFromResultSet = new Product();
            productFromResultSet.setName(listOfProducts.getString(1));
            productFromResultSet.setPrice(listOfProducts.getDouble(2));
            productFromResultSet.setRate(listOfProducts.getDouble(3));
            product = productFromResultSet;
        }
        return product;
    }
    public static void addOrderToDB(Product randomProduct) throws SQLException {
        String addOrder = String.format("INSERT INTO ORDERS(NAME, PRICE, RATE) VALUES(?, ?, ?);");
        PreparedStatement preparedStatementAddProduct = DBCONNECTION.prepareStatement(addOrder);
        preparedStatementAddProduct.setString(1, randomProduct.getName());
        preparedStatementAddProduct.setDouble(2, randomProduct.getPrice());
        preparedStatementAddProduct.setDouble(3, randomProduct.getRate());
        preparedStatementAddProduct.executeUpdate();
    }
    public static void getOrdersFromDB() throws SQLException {
        String printOrders = String.format("SELECT NAME, PRICE, RATE FROM ORDERS");
        PreparedStatement preparedStatement = DBCONNECTION.prepareStatement(printOrders);
        ResultSet listOfOrders = preparedStatement.executeQuery(printOrders);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                           LIST OF ORDERS");
        System.out.println("-------------------------------------------------------------------");
        while (listOfOrders.next()){
            Product order = new Product();
            order.setName(listOfOrders.getString(1));
            order.setPrice(listOfOrders.getDouble(2));
            order.setRate(listOfOrders.getDouble(3));
            System.out.println(order);
        }
    }
    public static void clearOrdersInDB() throws SQLException {
        String clearTableOrders = String.format("DELETE FROM ORDERS;");
        PreparedStatement preparedStatementClearTableOrders = DBCONNECTION.prepareStatement(clearTableOrders);
        preparedStatementClearTableOrders.executeUpdate();
    }
}
