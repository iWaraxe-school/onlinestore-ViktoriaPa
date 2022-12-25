package by.issoft.consoleApp.BD;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.storeHelper.RandomProductGenerator;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static by.issoft.consoleApp.BD.ConfigsTestStore.*;
import static org.reflections.scanners.Scanners.SubTypes;

public class DataBaseHandler {
    public static void fillStoreDB (){
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

            DataBaseHandler.dropTables(dbConnection);
            DataBaseHandler.createTables(dbConnection);
            DataBaseHandler.fillDB(dbConnection);
            DataBaseHandler.printProductsWithCategories(dbConnection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void fillDB (Connection connection) throws SQLException {
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
            PreparedStatement preparedStatementAddCategory = connection.prepareStatement(addCategory);
            preparedStatementAddCategory.setString(1, category.getName());
            preparedStatementAddCategory.executeUpdate();

            String idBookCategory = String.format("SELECT ID FROM CATEGORIES WHERE NAME = '%s'", category.getName());
            PreparedStatement preparedStatementIdCategory = connection.prepareStatement(idBookCategory);
            ResultSet resultSet = preparedStatementIdCategory.executeQuery(idBookCategory);

            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("ID");
            }

            RandomProductGenerator products = new RandomProductGenerator();
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10); i++) {
                String addProduct = String.format("INSERT INTO PRODUCTS(CATEGORY_ID, NAME, PRICE, RATE) VALUES(?, ?, ?, ?);");
                PreparedStatement preparedStatementAddProduct = connection.prepareStatement(addProduct);
                preparedStatementAddProduct.setInt(1, id);
                preparedStatementAddProduct.setString(2, products.generateProductName(category.getName()));
                preparedStatementAddProduct.setDouble(3, products.generateProductPrice());
                preparedStatementAddProduct.setDouble(4, products.generateProductRate());
                preparedStatementAddProduct.executeUpdate();
            }
        }
    }
    public static void dropTables (Connection connection) throws SQLException {
        String dropTableProducts = String.format("DROP TABLE IF EXISTS PRODUCTS;");
        PreparedStatement preparedStatementDropTableProducts = connection.prepareStatement(dropTableProducts);
        preparedStatementDropTableProducts.executeUpdate();

        String dropTableCategories = String.format("DROP TABLE IF EXISTS CATEGORIES;");
        PreparedStatement preparedStatementDropTableCategories = connection.prepareStatement(dropTableCategories);
        preparedStatementDropTableCategories.executeUpdate();
    }
    public static void createTables (Connection connection) throws SQLException {
        String createTableCategories = String.format("CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "NAME VARCHAR(255) NOT NULL);");
        PreparedStatement preparedStatementCreateTableCategories = connection.prepareStatement(createTableCategories);
        preparedStatementCreateTableCategories.executeUpdate();

        String createTableProducts = String.format("CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL," +
                "NAME VARCHAR(45) NOT NULL," +
                "PRICE DOUBLE NOT NULL," +
                "RATE DOUBLE NOT NULL," +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));");
        PreparedStatement preparedStatementCreateTableProducts = connection.prepareStatement(createTableProducts);
        preparedStatementCreateTableProducts.executeUpdate();
    }
    public static void printProducts(Connection connection) throws SQLException {
        String printFullListOfProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS");
        PreparedStatement preparedStatement = connection.prepareStatement(printFullListOfProducts);
        ResultSet listOfProducts = preparedStatement.executeQuery(printFullListOfProducts);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                 FULL LIST OF PRODUCTS FROM DATA BASE");
        System.out.println("-------------------------------------------------------------------");
        while (listOfProducts.next()){
            Product product = new Product();
            product.setName(listOfProducts.getString(1));
            product.setPrice(listOfProducts.getDouble(2));
            product.setRate(listOfProducts.getDouble(3));
            System.out.println(product);
        }
    }
    public static void printProductsWithCategories(Connection connection) throws SQLException {
        String printCategories = String.format("SELECT ID, NAME FROM CATEGORIES");
        PreparedStatement preparedStatement = connection.prepareStatement(printCategories);
        ResultSet setOfCategories = preparedStatement.executeQuery(printCategories);
        while (setOfCategories.next()){
            String nameOfCategory = setOfCategories.getString(2);
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Category: " + nameOfCategory);

            String printProducts = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS WHERE CATEGORY_ID = %s", setOfCategories.getString(1));
            PreparedStatement preparedStatement1 = connection.prepareStatement(printProducts);
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
}
