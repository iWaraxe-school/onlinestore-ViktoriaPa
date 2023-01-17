package by.issoft.store.http.client;

import by.issoft.domain.Product;
import by.issoft.store.BD.DataBaseHandler;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.sql.SQLException;

public class Client {
    public static void clientMakesOrder() throws SQLException {
        Product orderedProduct = DataBaseHandler.getRandomProduct();
        Gson g = new Gson();
        String productInJson = g.toJson(orderedProduct);

        RestAssured.baseURI = "http://localhost:8088";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.auth().basic("admin", "admin");
        request.body(productInJson);

        Response response = request.post("/order");
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response body: " + response.getBody().asString());
    }
}
