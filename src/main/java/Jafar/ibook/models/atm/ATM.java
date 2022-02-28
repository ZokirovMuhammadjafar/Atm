package Jafar.ibook.models.atm;

import Jafar.ibook.enums.card.Currency;
import lombok.Getter;
import lombok.Setter;
import Jafar.ibook.enums.Status;

import Jafar.ibook.enums.card.Kupyura;
import Jafar.ibook.models.Auditable;

import java.util.ArrayList;


@Getter
@Setter
public class ATM extends Auditable {
    private String location;
    private Status status;
    private final ArrayList<Cassette> cassetes = new ArrayList<>(6);


    public ATM(String location) {
        this.location = location;
        this.status = Status.ACTIVE;
        cassetes.add(new Cassette(Currency.USD, (short)10, Kupyura.YUZ, Status.ACTIVE, this.getId()));
        cassetes.add(new Cassette(Currency.USD, (short)10, Kupyura.ELLIK, Status.ACTIVE, this.getId()));
        cassetes.add(new Cassette(Currency.UZS, (short)10, Kupyura.YUZ, Status.ACTIVE, this.getId()));
        cassetes.add(new Cassette(Currency.UZS, (short)10, Kupyura.ELLIK, Status.ACTIVE, this.getId()));
        cassetes.add(new Cassette(Currency.UZS, (short)10, Kupyura.YIGIRMA, Status.ACTIVE, this.getId()));
        cassetes.add(new Cassette(Currency.UZS, (short)10, Kupyura.UN, Status.ACTIVE, this.getId()));
    }
}
