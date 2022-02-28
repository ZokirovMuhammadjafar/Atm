package Jafar.ibook.models.branch;

import Jafar.ibook.enums.Status;
import Jafar.ibook.models.Auditable;
import Jafar.ibook.models.auth.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
public class Branch extends Auditable {
    private String name;
    private String location;
    private Status status;
    private List<AuthUser> adminList;



}
