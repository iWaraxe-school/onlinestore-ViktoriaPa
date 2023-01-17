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
        server.bind(new InetSocketAddress(8088), 0);

        HttpContext contextMainPage = server.createContext("/", new MainPage());
        HttpContext contextTop5Page = server.createContext("/top5", new Top5());
        HttpContext contextSortedPage = server.createContext("/sorted", new SortedProducts());
        HttpContext contextOrderPage = server.createContext("/order", new Order());

        contextMainPage.setAuthenticator(new Auth("get"));
        contextTop5Page.setAuthenticator(new Auth("get"));
        contextSortedPage.setAuthenticator(new Auth("get"));
        contextOrderPage.setAuthenticator(new Auth("get"));
        server.setExecutor(null);
        server.start();
    }
}
