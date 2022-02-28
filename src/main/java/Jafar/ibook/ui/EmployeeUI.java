package Jafar.ibook.ui;

import Jafar.ibook.enums.Status;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.hr.HRService;

import java.util.ArrayList;
import java.util.Objects;

import static uz.jl.utils.Color.RED;
import static uz.jl.utils.Input.getStr;
import static uz.jl.utils.Print.println;


public class EmployeeUI extends BaseAbstractUI implements BaseUI {

    private static EmployeeUI employeeUI;

    public static EmployeeUI getInstance() {
        if (Objects.isNull(employeeUI)) {
            return employeeUI = new EmployeeUI();
        }
        return employeeUI;
    }

    private EmployeeUI() {
    }

    @Override
    public void create() {
        String username = getStr("Enter username : ");
        String password = getStr("Enter password : ");
        ResponseEntity<String> response = HRService.create(username, password);
        show(response);
    }

    @Override
    public void block() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = HRService.changeStatus(username, Status.BLOCKED);
        show(response);
    }

    @Override
    public void unblock() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = HRService.changeStatus(username, Status.ACTIVE);
        show(response);
    }

    @Override
    public void delete() {
        list();
        String username = getStr("Enter username : ");
        ResponseEntity<String> response = HRService.changeStatus(username, Status.DELETED);
        show(response);
    }

    @Override
    public void list() {
        ResponseEntity<ArrayList<AuthUser>> response = HRService.list();
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
