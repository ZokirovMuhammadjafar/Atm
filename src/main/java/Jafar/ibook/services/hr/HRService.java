package Jafar.ibook.services.hr;

import Jafar.ibook.configs.AppConfig;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.filesystems.DB;

import java.util.ArrayList;
import java.util.List;

import static Jafar.ibook.Validation.ValidationService.checkValidUsername;


/**
 * @author Bekpulatov Shoxruh, Fri 3:01 PM. 12/10/2021
 */
public class HRService {


    public static ResponseEntity<String> create(String username, String password) {
        if (checkValidUsername(username)) {
            return new ResponseEntity<>("Username already taken!!!", Status.HTTP_FORBIDDEN);
        }
        AuthUser employee = new AuthUser(username, password, Jafar.ibook.enums.Status.ACTIVE,
                Role.EMPLOYEE, "Not initialized", AppConfig.language);
        DB.getUsers().add(employee);
        return new ResponseEntity<>("Successfully created!!!");
    }

    public static ResponseEntity<ArrayList<AuthUser>> list() {
        List<AuthUser> allusers = DB.getUsers();
        ArrayList<AuthUser> listEmployee = new ArrayList<>();
        for (AuthUser user : allusers) {
            if (user.getRole().equals(Role.EMPLOYEE)) listEmployee.add(user);
        }
        return new ResponseEntity<>(listEmployee);
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
}
