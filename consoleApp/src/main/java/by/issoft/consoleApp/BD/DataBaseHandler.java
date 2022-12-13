package by.issoft.consoleApp.BD;

import by.issoft.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHandler {
    public static void addProductToDB (Connection connection, Product product) throws SQLException {
        String insert = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES(?, ?, ?)",
                Const.USER_TABLE,
                Const.USER_NAME,
                Const.USER_PRICE,
                Const.USER_RATE);
        PreparedStatement preparedStatement = connection.prepareStatement(insert);

        String price = String.valueOf(product.getPrice());
        String rate = String.valueOf(product.getRate());

        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, price);
        preparedStatement.setString(3, rate);

        preparedStatement.executeUpdate();
    }
    public static void clearTable (Connection connection) throws SQLException {
        String delete = String.format("DELETE FROM %s", Const.USER_TABLE);
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.executeUpdate();
    }
    public static void printProucts (Connection connection) throws SQLException {
        String print = String.format("SELECT * FROM %s", Const.USER_TABLE);
        PreparedStatement preparedStatement = connection.prepareStatement(print);
        ResultSet resultSet = preparedStatement.executeQuery(print);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                    LIST OF PRODUCTS FROM DATA BASE");
        System.out.println("-------------------------------------------------------------------");
        while (resultSet.next()){
            Product product = new Product();
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getDouble(3));
            product.setRate(resultSet.getDouble(4));
            System.out.println(product);
        }
    }
}
