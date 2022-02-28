package Jafar.ibook.repository.auth;

import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.repository.BaseRepository;
import Jafar.ibook.services.filesystems.DB;

import java.util.List;


public class AuthUserRepository extends BaseRepository<AuthUser> {

    public AuthUser findByUserName(String username) {
        for (AuthUser user : DB.getUsers()){
            if (user.getUsername().equalsIgnoreCase(username)) return user;
        }
        return null;
    }
    private static AuthUserRepository instance;
    public static AuthUserRepository getInstance() {
        if (instance == null) {
            instance = new AuthUserRepository();
        }
        return instance;
    }
    @Override
    public void save(AuthUser user) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(AuthUser authUser) {

    }

    @Override
    public AuthUser get() {
        return null;
    }

    @Override
    public List<AuthUser> getAll() {
        return null;
    }
}
