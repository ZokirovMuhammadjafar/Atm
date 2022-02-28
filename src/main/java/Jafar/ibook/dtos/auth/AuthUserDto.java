package Jafar.ibook.dtos.auth;

import Jafar.ibook.dtos.BaseGenericDto;
import Jafar.ibook.models.auth.AuthUser;
import lombok.Getter;
import lombok.Setter;





@Getter
@Setter
public class AuthUserDto extends BaseGenericDto {
    private String username;
    private String phoneNumber;

    //    private List<Cards> cards;
    // add some other fields
    public static void main(String[] args) {
        AuthUser authUser = new AuthUser();
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setId(authUser.getId());
        authUserDto.setUsername(authUser.getUsername());
        authUserDto.setPhoneNumber(authUser.getPhoneNumber());

    }
}
