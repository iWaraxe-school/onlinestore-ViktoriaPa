package by.issoft.store.http.client;

import by.issoft.domain.Product;
import by.issoft.store.BD.DataBaseHandler;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.sql.SQLException;

public class Client {
    public static void clientMakesOrder() throws SQLException {
        Product orderedProduct = DataBaseHandler.getRandomProduct();
        Gson g = new Gson();
        String productInJson = g.toJson(orderedProduct);
        System.out.println(productInJson);

        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(productInJson);
        request.post("/order");

        Response response = request.post("/order");
        System.out.println("The status received: " + response.statusLine());
        ResponseBody body = response.getBody();
        System.out.println(body.asString());

    }
}
