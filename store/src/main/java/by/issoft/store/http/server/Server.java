package by.issoft.store.http.server;

import by.issoft.store.http.pages.MainPage;
import by.issoft.store.http.pages.Order;
import by.issoft.store.http.pages.SortedProducts;
import by.issoft.store.http.pages.Top5;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);

        HttpContext contextMainPage = server.createContext("/", new MainPage());
        server.createContext("/top5", new Top5());
        server.createContext("/sorted", new SortedProducts());
        server.createContext("/order", new Order());

//        contextMainPage.setAuthenticator(new Auth("get"));
        server.setExecutor(null);
        server.start();
    }
}
