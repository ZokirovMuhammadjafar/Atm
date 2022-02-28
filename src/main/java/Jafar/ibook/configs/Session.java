package Jafar.ibook.configs;

import Jafar.ibook.models.auth.AuthUser;
import lombok.Getter;
import lombok.Setter;



public class Session {
    private static Session session;

    @Getter
    @Setter
    private AuthUser user;

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
            session.setUser(new AuthUser());
        }
        return session;
    }
}
