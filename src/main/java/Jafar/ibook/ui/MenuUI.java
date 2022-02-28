package Jafar.ibook.ui;

import Jafar.ibook.configs.LangConfig;
import Jafar.ibook.configs.Session;
import Jafar.ibook.enums.auth.Role;
import Jafar.ibook.enums.settings.Language;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.ui.components.MenuVal;
import uz.jl.utils.Color;
import uz.jl.utils.Print;


import java.util.LinkedHashMap;
import java.util.Map;


public class MenuUI {

    private static Map<String, String> menus() {
        Map<String, String> menus = new LinkedHashMap<>();
        AuthUser user = Session.getInstance().getUser();
        Role role = user.getRole();
        Language language = user.getLanguage();
        if (Role.ANONYMOUS.equals(role)) {
            menus.put(LangConfig.get("login"), MenuVal.LOGIN);
        }
        if (Role.SUPER_ADMIN.equals(role)) {
            // TODO: 12/8/2021 need to translation
            menus.put(LangConfig.get("branch.create"), MenuVal.BRANCH_CREATE);
            menus.put(LangConfig.get("branch.delete"), MenuVal.BRANCH_DELETE);
            menus.put(LangConfig.get("branch.block"), MenuVal.BRANCH_BLOCK);
            menus.put(LangConfig.get("branch.unblock"), MenuVal.BRANCH_UNBLOCK);
            menus.put(LangConfig.get("branch.list"), MenuVal.BRANCH_LIST);

            menus.put(LangConfig.get("admin.create"), MenuVal.ADMIN_CREATE);
            menus.put(LangConfig.get("admin.block"), MenuVal.ADMIN_BLOCK);
            menus.put(LangConfig.get("admin.unblock"), MenuVal.ADMIN_UNBLOCK);
            menus.put(LangConfig.get("admin.delete"), MenuVal.ADMIN_DELETE);
            menus.put(LangConfig.get("admin.list"), MenuVal.ADMIN_LIST);
        } else if (Role.ADMIN.equals(role)) {
            menus.put(LangConfig.get("hr.create"), MenuVal.HR_CREATE);
            menus.put(LangConfig.get("hr.block"), MenuVal.HR_BLOCK);
            menus.put(LangConfig.get("hr.unblock"), MenuVal.HR_UNBLOCK);
            menus.put(LangConfig.get("hr.delete"), MenuVal.HR_DELETE);
            menus.put(LangConfig.get("hr.list"), MenuVal.HR_LIST);

            menus.put(LangConfig.get("atm.create"), MenuVal.ATM_CREATE);
            menus.put(LangConfig.get("atm.block"), MenuVal.ATM_BLOCK);
            menus.put(LangConfig.get("atm.unblock"), MenuVal.ATM_UNBLOCK);
            menus.put(LangConfig.get("atm.delete"), MenuVal.ATM_DELETE);
            menus.put(LangConfig.get("atm.list"), MenuVal.ATM_LIST);
        } else if (Role.HR.equals(role)) {
            menus.put(LangConfig.get("employee.create"), MenuVal.EMPLOYEE_CREATE);
            menus.put(LangConfig.get("employee.block"), MenuVal.EMPLOYEE_BLOCK);
            menus.put(LangConfig.get("employee.unblock"), MenuVal.EMPLOYEE_UNBLOCK);
            menus.put(LangConfig.get("employee.delete"), MenuVal.EMPLOYEE_DELETE);
            menus.put(LangConfig.get("employee.list"), MenuVal.EMPLOYEE_LIST);
        } else if (Role.EMPLOYEE.equals(role)) {
            menus.put(LangConfig.get("client.create"), MenuVal.CLIENT_CREATE);
            menus.put(LangConfig.get("client.block"), MenuVal.CLIENT_BLOCK);
            menus.put(LangConfig.get("client.unblock"), MenuVal.CLIENT_UNBLOCK);
            menus.put(LangConfig.get("client.delete"), MenuVal.CLIENT_DELETE);
            menus.put(LangConfig.get("client.list"), MenuVal.CLIENT_LIST);

            menus.put(LangConfig.get("give.cards"), MenuVal.GIVE_CARDS);
            menus.put(LangConfig.get("atm.update"), MenuVal.UPDATE_ATM);
            menus.put(LangConfig.get("atm.cassette.block"), MenuVal.BLOCK_ATM_CASSETTE);
            menus.put(LangConfig.get("atm.cassette.unblock"), MenuVal.UNBLOCK_ATM_CASSETTE);
        }
        if (!Role.ANONYMOUS.equals(role)) {
            menus.put(LangConfig.get("card.order"), MenuVal.CLIENT_ORDER_CARD);
            menus.put(LangConfig.get("profile"), MenuVal.PROFILE);
            menus.put(LangConfig.get("logout"), MenuVal.LOGOUT);
        }

        menus.put(LangConfig.get("atm.service"), MenuVal.ATM_SERVICES);
        menus.put(LangConfig.get("quit"), MenuVal.QUIT);
        return menus;
    }

    public static void show() {
        menus().forEach((k, v) -> Print.println(Color.GREEN, (k + " - > " + v)));
    }

}
