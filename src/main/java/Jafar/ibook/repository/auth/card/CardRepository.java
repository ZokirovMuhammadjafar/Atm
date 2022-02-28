package Jafar.ibook.repository.auth.card;

import Jafar.ibook.configs.Session;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.configs.Session;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.repository.BaseRepository;
import Jafar.ibook.services.filesystems.DB;

import java.util.List;

public class CardRepository extends BaseRepository<Card> {
    private static CardRepository instance;
    public static CardRepository getInstance() {
        if (instance == null) {
            instance = new CardRepository();
        }
        return instance;
    }

    @Override
    public void save(Card card) {
        getAll().add(card);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(Card card) {

    }

    @Override
    public Card get() {
        for (Card card : getAll()) {
            if(card.getOwnerId().equals(Session.getInstance().getUser().getId())){
                return card;
            }

        }return null;
    }

    @Override
    public List<Card> getAll() {
        return DB.getCards();
    }

}
