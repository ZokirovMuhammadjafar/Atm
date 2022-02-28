package Jafar.ibook;

import Jafar.ibook.ui.*;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import static Jafar.ibook.ui.components.MenuVal.*;



public class App {
    static AdminUI adminUI;
    static AtmUI atmUI;
    static AuthUI authUI;
    static BranchUI branchUI;
    static ClientUI clientUI;
    static EmployeeUI employeeUI;
    static HrUI hrUI;

    static {
        adminUI = AdminUI.getInstance();
        atmUI = AtmUI.getInstance();
        authUI = AuthUI.getInstance();
        branchUI = BranchUI.getInstance();
        clientUI = ClientUI.getInstance();
        employeeUI = EmployeeUI.getInstance();
        hrUI = HrUI.getInstance();


    }
    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        MenuUI.show();
        String choice = Input.getStr("?->");
        switch (choice.toUpperCase()) {
            case BRANCH_CREATE -> branchUI.create();
            case BRANCH_BLOCK -> branchUI.block();
            case BRANCH_UNBLOCK -> branchUI.unblock();
            case BRANCH_DELETE -> branchUI.delete();
            case BRANCH_LIST -> branchUI.list();

            case ADMIN_CREATE -> adminUI.create();
            case ADMIN_BLOCK -> adminUI.block();
            case ADMIN_UNBLOCK -> adminUI.unblock();
            case ADMIN_DELETE -> adminUI.delete();
            case ADMIN_LIST -> adminUI.list();

            case HR_CREATE -> hrUI.create();
            case HR_BLOCK -> hrUI.block();
            case HR_UNBLOCK -> hrUI.unblock();
            case HR_DELETE -> hrUI.delete();
            case HR_LIST -> hrUI.list();

            case EMPLOYEE_CREATE -> employeeUI.create();
            case EMPLOYEE_BLOCK -> employeeUI.block();
            case EMPLOYEE_UNBLOCK -> employeeUI.unblock();
            case EMPLOYEE_DELETE -> employeeUI.delete();
            case EMPLOYEE_LIST -> employeeUI.list();

            // -----------------
            case CLIENT_CREATE -> clientUI.create();
            case CLIENT_BLOCK -> clientUI.block();
            case CLIENT_UNBLOCK -> clientUI.unblock();
            case CLIENT_DELETE -> clientUI.delete();
            case CLIENT_LIST -> clientUI.list();

            case ATM_UNBLOCK -> atmUI.unblock();

            // - menuyuda yoq ekan
            case CLIENT_ORDER_CARD -> clientUI.orderCard();

            case ATM_CREATE -> atmUI.create();
            case ATM_BLOCK -> atmUI.block();
            case ATM_DELETE -> atmUI.delete();
            case ATM_LIST -> atmUI.list();
            case ATM_SERVICES -> atmUI.services();

            case LOGIN -> authUI.login();
            case LOGOUT -> authUI.logout();
            case PROFILE -> authUI.profile();
            case QUIT -> {
                authUI.quit();
                return;
            }

            default -> // TODO: 12/8/2021 translate
                    Print.println(Color.RED, "Wrong Choice");
        }
        main(args);
    }

}
