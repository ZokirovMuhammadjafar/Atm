package Jafar.ibook.services.admin;

import Jafar.ibook.configs.AppConfig;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.filesystems.DB;

import java.util.ArrayList;
import java.util.List;

import static Jafar.ibook.Validation.ValidationService.checkValidUsername;


public class AdminService {
    public static ResponseEntity<String> create(String username, String password) {
        if (checkValidUsername(username)) {
            return new ResponseEntity<>("Username already taken!!!", Status.HTTP_FORBIDDEN);
        }
        AuthUser HR = new AuthUser(username, password, Jafar.ibook.enums.Status.ACTIVE,
                Role.HR, "Not initialized", AppConfig.language);
        DB.getUsers().add(HR);
        return new ResponseEntity<>("Successfully created!!!");
    }

    public static ResponseEntity<String> changeStatus(String username, Jafar.ibook.enums.Status status) {
        if (!checkValidUsername(username)) {
            return new ResponseEntity<>("User not found!!!", Status.HTTP_NOT_FOUND);
        }
        for (AuthUser user : DB.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) user.setStatus(status);
            return new ResponseEntity<>("Successfully Status changed!!!");
        }
        return new ResponseEntity<>("Something went wrong!!!", Status.HTTP_BAD_REQUEST);
    }

    public static ResponseEntity<ArrayList<AuthUser>> list() {
        List<AuthUser> allusers = DB.getUsers();
        ArrayList<AuthUser> listHRs = new ArrayList<>();
        for (AuthUser user : allusers) {
            if (user.getRole().equals(Role.HR)) listHRs.add(user);
        }
        return new ResponseEntity<>(listHRs);
    }
}
