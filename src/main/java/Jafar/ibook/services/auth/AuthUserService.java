package Jafar.ibook.services.auth;

import Jafar.ibook.configs.Session;
import Jafar.ibook.dtos.auth.AuthUserDto;
import Jafar.ibook.mapper.auth.AuthUserMapper;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.repository.auth.AuthUserRepository;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.BaseAbstractService;
import Jafar.ibook.services.filesystems.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthUserService extends BaseAbstractService<AuthUser, AuthUserRepository, AuthUserMapper> {
    private static AuthUserService authUserService;

    private AuthUserService(AuthUserRepository repository, AuthUserMapper mapper) {
        super(repository, mapper);
    }

    public static AuthUserService getInstance() {
        if (authUserService == null) {
            authUserService = new AuthUserService(AuthUserRepository.getInstance(), AuthUserMapper.getInstance());
        }
        return authUserService;
    }

    @Override
    public ResponseEntity<List<AuthUser>> getAll() {
        List<AuthUser> users = new ArrayList<>();
        return new ResponseEntity<>(users, Status.HTTP_NOT_FOUND);
    }

    @Override
    public ResponseEntity<AuthUser> get(String id) {
        return new ResponseEntity<>(new AuthUser());
    }

    public ResponseEntity<String> login(String username, String password) {
        AuthUser user = repository.findByUserName(username);
        if (Objects.isNull(user) || !user.getPassword().equals(password) ) {
            return new ResponseEntity<>("Bad Credentials", Status.HTTP_FORBIDDEN);
        }
        else if( !user.getStatus().equals(Jafar.ibook.enums.Status.ACTIVE)){
            return new ResponseEntity<>("Something went wrong.");
        }
        Session.getInstance().setUser(user);
        return new ResponseEntity<>("Successfully");
    }

    public ResponseEntity<String> logout() {
        Session.getInstance().setUser(new AuthUser());
        return new ResponseEntity<>("Successfully logged out!!!");
    }

    public ResponseEntity<AuthUserDto> profile() {
        return new ResponseEntity<>(AuthUserMapper.getInstance().toDto(Session.getInstance().getUser()));
    }

    public ResponseEntity<ArrayList<Card>> getSessionCards() {
        List<Card> allCards = DB.getCards();
        ArrayList<Card> sessionCards = new ArrayList<>();
        for (Card allCard : allCards) {
            if (allCard.getOwnerId().equals(Session.getInstance().getUser().getId()))
                sessionCards.add(allCard);
        }
        return new ResponseEntity<>(sessionCards);
    }

}
