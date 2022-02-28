package Jafar.ibook.services.superAdmin;

import Jafar.ibook.models.branch.Branch;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.filesystems.DB;
import uz.jl.utils.Color;
import uz.jl.utils.Print;


public class BranchService {


    public static ResponseEntity<String> create(String location) {

        return new ResponseEntity<>("Successfully created!");
    }


    public static ResponseEntity<String> showList() {
        int i=1;
        for (Branch branch : DB.getBranches()) {
            Print.println(Color.BLUE,i+++". "+branch.getName());
        }
        if(i==1){
            return  new ResponseEntity<>("Branches not created yet.", Status.HTTP_NOT_FOUND);
        }
        return new ResponseEntity<>(" Here all branches.");
    }
}
