package Jafar.ibook.Validation;

import Jafar.ibook.enums.card.CardType;
import Jafar.ibook.enums.card.CardType;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.models.branch.Branch;
import Jafar.ibook.services.filesystems.DB;

public class ValidationService {

    public static CardType checkValidCardtype(String cardtype) {
        for (CardType value : CardType.values()) {
            if (value.toString().equalsIgnoreCase(cardtype)) {
                return value;
            }
        }
        return null;
    }

    public static boolean checkValidPassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < 48 || password.charAt(i) > 57 || password.length() > 4)
                return true;
        }
        return false;
    }

    public static boolean checkValidUsername(String username) {
        for (AuthUser user : DB.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static boolean hasBranches() {
       return DB.getBranches().size()!=0;
    }

    public static Branch validBranchName(String branchName) {
        for (Branch branch : DB.getBranches()) {
            if(branch.getName().equalsIgnoreCase(branchName))
                return branch;
        }
        return null;
    }
}
