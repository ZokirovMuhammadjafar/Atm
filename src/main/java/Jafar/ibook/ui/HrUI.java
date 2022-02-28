package Jafar.ibook.ui;

import Jafar.ibook.enums.Status;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.admin.AdminService;

import java.util.ArrayList;
import java.util.Objects;

import static uz.jl.utils.Color.RED;
import static uz.jl.utils.Input.getStr;
import static uz.jl.utils.Print.println;


public class HrUI extends BaseAbstractUI implements BaseUI {

    private static HrUI hrUI;

    public static HrUI getInstance() {
        if (Objects.isNull(hrUI)) {
            return hrUI = new HrUI();
        }
        return hrUI;
    }

    private HrUI() {
    }

    @Override
    public void create() {
        String username = getStr("Enter username : ");
        String password = getStr("Enter password : ");
        ResponseEntity<String> response = AdminService.create(username, password);
        show(response);

    }

    @Override
    public void block() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = AdminService.changeStatus(username, Status.BLOCKED);
        show(response);
    }

    @Override
    public void unblock() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = AdminService.changeStatus(username, Status.ACTIVE);
        show(response);
    }

    @Override
    public void delete() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = AdminService.changeStatus(username, Status.DELETED);
        show(response);
    }

    @Override
    public void list() {
        ResponseEntity<ArrayList<AuthUser>> response = AdminService.list();
        ArrayList<AuthUser> users = response.getData();
        int i = 1;
        for (AuthUser user : users) {
            println(i++ + ". " + user.getUsername());
        }
        if (i == 1) {
            println(RED, "There ara not any HRs yet!!!");
        }
    }
}
