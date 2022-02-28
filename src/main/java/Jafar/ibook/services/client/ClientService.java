package Jafar.ibook.services.client;

import Jafar.ibook.Validation.ValidationService;
import Jafar.ibook.configs.Session;
import Jafar.ibook.enums.card.CardType;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.response.Status;
import Jafar.ibook.services.filesystems.DB;
import Jafar.ibook.utils.BaseUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;


public class ClientService {

    public static ResponseEntity<String> orderCard(String cardType, String password) {
        CardType cardtype = ValidationService.checkValidCardtype(cardType);
        if (Objects.isNull(cardtype)) {
            return new ResponseEntity<>("CardType Not Found", Status.HTTP_NOT_FOUND);
        }
        if (ValidationService.checkValidPassword(password)) {
            return new ResponseEntity<>("Bad credential!!!", Status.HTTP_BAD_REQUEST);

        }
        String pan = cardtype.getCode() + BaseUtils.generateCardPan();
        Calendar calendar = new GregorianCalendar();
        String expiry = calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) % 100;
        Card card = new Card(pan, expiry, password, cardtype, Jafar.ibook.enums.Status.ACTIVE,  Session.getInstance().getUser().getId(),10000L, cardtype.getCurrency(), new ArrayList<>());
        DB.getCards().add(card);

        return new ResponseEntity<>("Successfully Card created!!!");
    }
}
