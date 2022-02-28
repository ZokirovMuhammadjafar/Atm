package Jafar.ibook.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;




@Getter
@AllArgsConstructor
public enum CardType {
    UZCARD("8600", Currency.UZS),
    HUMO("9860", Currency.UZS),
    COBAGE("6320", Currency.UZS),
    VISA("4790", Currency.USD),
    MASTER_CARD("5471", Currency.USD),
    UNION_PAY("4567", Currency.USD);
    private final String code;
    private final Currency currency;

}
