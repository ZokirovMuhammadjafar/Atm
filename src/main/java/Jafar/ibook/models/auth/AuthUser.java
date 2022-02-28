package Jafar.ibook.models.auth;

import Jafar.ibook.models.Auditable;
import lombok.*;
import Jafar.ibook.configs.AppConfig;
import Jafar.ibook.enums.Status;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.enums.settings.Language;
import Jafar.ibook.models.Auditable;

/**
 * @author Elmurodov Javohir, Mon 6:18 PM. 11/29/2021
 */
@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper = true)
@Builder(builderMethodName = "childBuilder")
public class AuthUser extends Auditable {
    private String username;
    private String password;
    private Status status;
    private Role role;
    private String phoneNumber;
    private Language language;

    public AuthUser() {
        super();
        this.language = AppConfig.language;
        this.role = Role.ANONYMOUS;
        this.status=Status.ACTIVE;
    }

    public AuthUser(String username, String password, Language language) {
        this.username=username;
        this.password=password;
        this.language=language;
    }
}
