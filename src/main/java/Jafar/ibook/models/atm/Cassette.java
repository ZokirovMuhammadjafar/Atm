package Jafar.ibook.models.atm;

import Jafar.ibook.enums.Status;
import Jafar.ibook.enums.card.Currency;
import Jafar.ibook.enums.card.Kupyura;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Cassette {
    private String id;
    private Currency currency;
    private Short currencyCount;
    private Kupyura kupyura;
    private Status status;
    private String atmId;

    public Cassette(Currency currency, Short currencyCount, Kupyura kupyura, Status status, String atmId) {
        this.currency = currency;
        this.currencyCount = currencyCount;
        this.kupyura = kupyura;
        this.status = status;
        this.atmId = atmId;
    }

}
