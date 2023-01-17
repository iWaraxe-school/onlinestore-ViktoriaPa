package by.issoft.store.http.pages;

import by.issoft.store.BD.DataBaseHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class MainPage implements HttpHandler {
    @SneakyThrows
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleResponse(exchange, "All available products in the store: \n\n" + DataBaseHandler.getProductsWithCategories());
    }
}
