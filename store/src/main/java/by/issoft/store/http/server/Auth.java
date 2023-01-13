package by.issoft.store.http.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {


    public Auth(String get) {
        super(get);
    }

    @Override
    public boolean checkCredentials(String user, String pwd) {
        return user.equals("admin") && pwd.equals("admin");
    }
}
