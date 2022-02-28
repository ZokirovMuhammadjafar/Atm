package Jafar.ibook.services.superAdmin;

import Jafar.ibook.Validation.ValidationService;
import Jafar.ibook.enums.Status;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.mapper.auth.AuthUserMapper;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.models.branch.Branch;
import Jafar.ibook.repository.auth.AuthUserRepository;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.BaseAbstractService;
import Jafar.ibook.services.filesystems.DB;

import java.util.List;
import java.util.Objects;


public class SuperAdminService extends BaseAbstractService<AuthUser, AuthUserRepository, AuthUserMapper> {
    private static SuperAdminService superAdminService;


    protected SuperAdminService(AuthUserRepository repository, AuthUserMapper mapper) {
        super(repository, mapper);
    }
    public static SuperAdminService getInstance() {
        if(Objects.isNull(superAdminService))
            superAdminService=new SuperAdminService(AuthUserRepository.getInstance(), AuthUserMapper.getInstance());
        return superAdminService;
    }


    public  static  ResponseEntity<String> create(String username,String password,String branchName) {
       if(ValidationService.checkValidUsername(username)){
           return  new ResponseEntity<>("Username already taken.", Jafar.ibook.response.Status.HTTP_NOT_FOUND);
       }
        else {
           Branch branch=ValidationService.validBranchName(branchName);
           if(Objects.nonNull(branch)) {
               AuthUser user1=new AuthUser();
               user1.setUsername(username);
               user1.setStatus(Status.ACTIVE);
               user1.setPassword(password);
               user1.setRole(Role.ADMIN);

               DB.getUsers().add(user1);
           }else {
               return new ResponseEntity<>("Branch not found with this name!");
           }
        }
        return new ResponseEntity<>("Admin successfully created.");
    }

    public static ResponseEntity<String> block(String name) {
            AuthUser user=AuthUserRepository.getInstance().findByUserName(name);
            if(Objects.isNull(user)){
                return new ResponseEntity<>("User not found.");
            }else {
                user.setStatus(Status.BLOCKED);
                return new ResponseEntity<>("User blocked.");
            }
    }

    public static ResponseEntity<String> unBlock(String name) {
        AuthUser user=AuthUserRepository.getInstance().findByUserName(name);
        if(Objects.isNull(user) || !user.getStatus().equals(Status.ACTIVE)){
            return new ResponseEntity<>("Something went wrong.");
        }
        user.setStatus(Status.ACTIVE);
        return new ResponseEntity<>("User unBlocked.");
    }

    public static ResponseEntity<String> delete(String name) {
        AuthUser user=AuthUserRepository.getInstance().findByUserName(name);
        if(Objects.isNull(user) || user.getStatus().equals(Status.DELETED)){
            return new ResponseEntity<>("User not found.");
        }
        user.setStatus(Status.DELETED);
        return new ResponseEntity<>("User removed.");
    }


    public ResponseEntity<List<AuthUser>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<AuthUser> get(String id) {
        return null;
    }
}
