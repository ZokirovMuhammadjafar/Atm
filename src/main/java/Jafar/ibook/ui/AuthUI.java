package Jafar.ibook.ui;

import Jafar.ibook.services.auth.AuthUserService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import Jafar.ibook.dtos.auth.AuthUserDto;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.auth.AuthUserService;
import uz.jl.utils.Input;


import java.util.ArrayList;
import java.util.Objects;

import static uz.jl.utils.Color.RED;
import static uz.jl.utils.Print.println;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUI extends BaseAbstractUI /*implements BaseUI*/ {
    private AuthUserService authUserService = AuthUserService.getInstance();
    private static AuthUI authUI;

    public static AuthUI getInstance() {
        if (Objects.isNull(authUI)) {
            return authUI = new AuthUI();
        }
        return authUI;
    }


    public void login() {
        String username = Input.getStr("Username : ");
        String password = Input.getStr("Password : ");
        ResponseEntity<String> response = authUserService.login(username, password);
        show(response);
    }

    public void logout() {
        ResponseEntity<String> response = authUserService.logout();
        show(response);
    }

    public void profile() {
        ResponseEntity<AuthUserDto> response = authUserService.profile();
        ResponseEntity<ArrayList<Card>> response1 = authUserService.getSessionCards();
        println("Username : " + response.getData().getUsername());
        println("User phoneNumber : " + response.getData().getPhoneNumber());
        println("User all Cards : ");
        int i = 1;
        for (Card datum : response1.getData()) {
            println(i++ + ". " + datum.getCardType().toString() + "****" + datum.getPan().substring(12, 16) + "    | " + datum.getCurrency());
        }
        if (i == 1) {
            println(RED, "There ara not any cards yet!!!");
        }
    }

    public void quit() {

    }
}
