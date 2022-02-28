package Jafar.ibook.ui;



import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.superAdmin.BranchService;

import java.util.Objects;

import static uz.jl.utils.Input.getStr;


public class BranchUI extends BaseAbstractUI implements BaseUI{

    private static BranchUI branchUI;

    public static BranchUI getInstance() {
        if (Objects.isNull(branchUI)){
            return branchUI=new BranchUI();
        }
        return branchUI;
    }

    private BranchUI() {
    }

    @Override
    public void create() {
        String location=getStr("Enter location: ");


        ResponseEntity<String> response= BranchService.create(location);


    }

    @Override
    public void block() {

    }

    @Override
    public void unblock() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void list() {
        ResponseEntity<String> response=BranchService.showList();
        show(response);
    }
}
