package Jafar.ibook.services.atm;

import Jafar.ibook.models.atm.ATM;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.filesystems.DB;

import java.util.List;


public class ATMService {
    public static ResponseEntity<String> create(String location) {
        ATM atm = new ATM(location);
        DB.getBankomats().add(atm);
        return new ResponseEntity<>("ATM successfully created!!!");
    }

    public static ResponseEntity<String> changeStatus(String location, String id, Jafar.ibook.enums.Status status) {
        List<ATM> bankomats = DB.getBankomats();
        for (ATM bankomat : bankomats) {
            if (bankomat.getLocation().equalsIgnoreCase(location) && bankomat.getId().substring(34).equalsIgnoreCase(id)) {
                bankomat.setStatus(Jafar.ibook.enums.Status.BLOCKED);
                return new ResponseEntity<>("Successfully Status changed!!!");
            }

        }
        return new ResponseEntity<>("ATM not found!!!", Status.HTTP_NOT_FOUND);
    }


    public static ResponseEntity<List<ATM>> list() {
        return new ResponseEntity<>(DB.getBankomats());
    }
}
