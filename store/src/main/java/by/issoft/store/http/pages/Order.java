package by.issoft.store.http.pages;
import by.issoft.domain.Product;
import by.issoft.domain.ProductBuilder;
import by.issoft.store.BD.DataBaseHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class Order implements HttpHandler {
    @SneakyThrows
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String orderedProductInJSON = null;
        if (exchange.getRequestMethod().equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            orderedProductInJSON = bufferedReader.readLine();
            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(orderedProductInJSON);
                String productName = obj.get("name").toString();
                String price = obj.get("price").toString();
                String rate = obj.get("rate").toString();
                Product orderedProduct = new ProductBuilder().name(productName).price(Double.parseDouble(price)).rate(Double.parseDouble(rate)).build();
                DataBaseHandler.addOrderToDB(orderedProduct);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            handleResponse(exchange, "You have ordered the following product: " + orderedProductInJSON);
        }
        if (exchange.getRequestMethod().equals("GET")) {
            handleResponse(exchange, "Ordered product: \n\n" + DataBaseHandler.getOrdersFromDB());
        } else {
            handleResponse(exchange, "Oops! Something went wrong");
        }
    }
}