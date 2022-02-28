package Jafar.ibook.dtos.auth;

import Jafar.ibook.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AuthUserCreateDto implements BaseDto {
    private String username;
    private String password;
    private String phoneNumber;
}
