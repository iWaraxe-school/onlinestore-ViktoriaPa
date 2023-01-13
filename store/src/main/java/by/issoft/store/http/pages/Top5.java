package by.issoft.store.http.pages;

import by.issoft.store.BD.DataBaseHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class Top5 implements HttpHandler {
    @SneakyThrows
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleResponse(exchange, "List of Top5 products: \n\n" + DataBaseHandler.getTop5Http());
    }
}
