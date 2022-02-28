package Jafar.ibook.models.card;

import lombok.*;
import Jafar.ibook.enums.Status;
import Jafar.ibook.enums.card.CardType;
import Jafar.ibook.enums.card.Currency;
import Jafar.ibook.models.Auditable;

import java.util.ArrayList;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"pan"}, callSuper = false)
public class Card extends Auditable {
    private String pan;
    private String expiry;
    private String password;
    private CardType cardType;
    private Status cardStatus;
    private String ownerId;
    private Long balance;
    private Currency currency;
    private ArrayList<String> phoneNumbers;

}
