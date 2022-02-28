package Jafar.ibook.ui;

import Jafar.ibook.enums.card.CardType;
import Jafar.ibook.response.ResponseEntity;
import Jafar.ibook.services.client.ClientService;
import uz.jl.utils.Print;

import java.util.Objects;

import static uz.jl.utils.Input.getStr;


public class ClientUI extends BaseAbstractUI implements BaseUI {

    private static ClientUI clientUI;

    public static ClientUI getInstance() {
        if (Objects.isNull(clientUI)) {
            return clientUI = new ClientUI();
        }
        return clientUI;
    }

    private ClientUI() {
    }
    public void orderCard() {
        for (CardType value : CardType.values()) {
            Print.println("🔹 " + value + "   | " + value.getCurrency().toString());
        }
        String cardtype = getStr("Enter CardType : ");
        String password = getStr("Enter password for new Card(Only four numbers) : ");
        ResponseEntity<String> response = ClientService.orderCard(cardtype, password);
        show(response);
    }

    @Override
    public void create() {

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

    }
}

