package Jafar.ibook.mapper.auth;

import Jafar.ibook.dtos.auth.AuthUserDto;
import Jafar.ibook.mapper.BaseMapper;
import Jafar.ibook.models.auth.AuthUser;


public class AuthUserMapper extends BaseMapper<AuthUser, AuthUserDto>   {

    public static AuthUserMapper instance;

    public static AuthUserMapper getInstance() {
        if (instance == null) {
            instance = new AuthUserMapper();
        }
        return instance;
    }

    @Override
    public AuthUser fromDto(AuthUserDto authUserDto) {
        return null;
    }

    @Override
    public AuthUserDto toDto(AuthUser authUser) {
          return new AuthUserDto(){{setUsername(authUser.getUsername());
          setPhoneNumber(authUser.getPhoneNumber());
          setId(authUser.getId());
          }};


    }
}
