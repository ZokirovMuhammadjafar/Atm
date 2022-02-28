package Jafar.ibook.models.employee;

import lombok.Getter;
import lombok.Setter;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.enums.settings.Language;
import Jafar.ibook.models.auth.AuthUser;

@Getter
@Setter
public class Employee extends AuthUser {
    private String branchID;

    public Employee(String username,String password,Language language,String branchID) {
        super( username, password, language);
        this.setRole(Role.EMPLOYEE);
        this.branchID = branchID;
    }

}
