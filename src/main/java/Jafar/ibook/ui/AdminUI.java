package Jafar.ibook.ui;

import Jafar.ibook.Validation.ValidationService;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.superAdmin.SuperAdminService;

import java.util.Objects;

import static uz.jl.utils.Input.getStr;


public class AdminUI extends BaseAbstractUI implements BaseUI  {


    private static AdminUI adminUI;

    public static AdminUI getInstance() {
        if (Objects.isNull(adminUI)){
            return adminUI=new AdminUI();
        }
        return adminUI;
    }

    private AdminUI() {

    }

    @Override
    public void create() {
        BranchUI.getInstance().list();
        if(ValidationService.hasBranches()){
            String branchName=getStr("Enter branch name to create admin: ");
            String username=getStr("Enter name: ");
            String password=getStr("Enter password: ");
            ResponseEntity<String > response= SuperAdminService.create(username,password,branchName);
            show(response);
        }

    }

    @Override
    public void block() {
        String name=getStr("Enter name of the admin to block: ");
        ResponseEntity<String> response=SuperAdminService.block(name);
        show(response);
    }

    @Override
    public void unblock() {
        String name=getStr("Enter name of the admin to unblock: ");
        ResponseEntity<String > response=SuperAdminService.unBlock(name);
        show(response);
    }

    @Override
    public void delete() {
        String name=getStr("Enter name of the admin to remove: ");
        ResponseEntity<String > response=SuperAdminService.delete(name);

    }

    @Override
    public void list() {

    }
}
