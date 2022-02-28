package Jafar.ibook.ui;

import Jafar.ibook.enums.Status;
import Jafar.ibook.models.atm.ATM;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.atm.ATMService;

import java.util.List;
import java.util.Objects;

import static Jafar.ibook.services.atm.AtmServices.menuAtm;
import static uz.jl.utils.Color.RED;
import static uz.jl.utils.Input.getStr;
import static uz.jl.utils.Print.println;


public class AtmUI extends BaseAbstractUI implements BaseUI {

    private static AtmUI atmUI;

    public static AtmUI getInstance() {
        if (Objects.isNull(atmUI)) {
            return atmUI = new AtmUI();
        }
        return atmUI;
    }

    private AtmUI() {
    }

    public void services() {
        menuAtm();
    }


    @Override
    public void create() {
        String location = getStr("Enter location : ");
        ResponseEntity<String> response = ATMService.create(location);
        show(response);
    }

    @Override
    public void block() {
        list();
        String location = getStr("Enter location : ");
        String id = getStr("Enter id : ");
        ResponseEntity<String> response = ATMService.changeStatus(location, id, Status.BLOCKED);
        show(response);

    }

    @Override
    public void unblock() {
        list();
        String location = getStr("Enter location : ");
        String id = getStr("Enter id : ");
        ResponseEntity<String> response = ATMService.changeStatus(location, id, Status.ACTIVE);
        show(response);
    }

    @Override
    public void delete() {
        list();
        String location = getStr("Enter location : ");
        String id = getStr("Enter id : ");
        ResponseEntity<String> response = ATMService.changeStatus(location, id, Status.DELETED);
        show(response);
    }

    @Override
    public void list() {
        ResponseEntity<List<ATM>> response = ATMService.list();
        List<ATM> atms = response.getData();
        int i = 1;
        for (ATM atm : atms) {
            println(i++ + ". " + atm.getLocation() + "   | id : " + atm.getId().substring(34));
        }
        if (i == 1) {
            println(RED, "There ara not any ATMs yet!!!");
        }

    }
}
