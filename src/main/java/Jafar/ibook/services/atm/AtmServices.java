package Jafar.ibook.services.atm;

import Jafar.ibook.enums.card.Currency;
import Jafar.ibook.models.atm.ATM;
import Jafar.ibook.models.atm.Cassette;
import Jafar.ibook.models.card.Card;
import Jafar.ibook.services.filesystems.DB;


import java.util.Locale;

import static Jafar.ibook.services.filesystems.DB.bankomats;
import static Jafar.ibook.services.filesystems.DB.getCards;
import static uz.jl.utils.Color.*;
import static uz.jl.utils.Input.getStr;
import static uz.jl.utils.Print.print;
import static uz.jl.utils.Print.println;

public class AtmServices {
    public static ATM atm;
    public static Card card;

    public static boolean checkPan() {
        if (bakomatFunction()) return true;
        String pan = getStr("enter pan: ");
        for (Card card1 : getCards()) {
            if (card1.getPan().equals(pan) && card1.getPassword().equals(getStr("enter pin"))) {
                card = card1;
                return false;
            }
        }
        println("error pan");
        return true;

    }

    private static boolean bakomatFunction() {
        bankomats.forEach(a ->
            println(GREEN, a.getLocation() + " " + a.getStatus())
        );
        String enter = getStr("enter location: ");
        boolean haqiqat = true;
        for (ATM bankomat : bankomats) {
            if (bankomat.getLocation().equals(enter)) {
                atm = bankomat;
                haqiqat = false;
                break;
            }
        }
        if (haqiqat) {
            println(RED, "error location");
            return true;
        }
        return false;
    }

    public static void menuAtm() {
        println(GREEN, "card_service");
        println(GREEN, "convert_money");
        println("quit");
        switch (getStr("?:")) {
            case "card_service" -> {
                if (checkPan()) menuAtm();
                enterMenu();
                writeAll();
            }
            case "convert_money" -> convertMoney();
            case "quit" -> {
                writeAll();
                println("good bye");
                return;
            }
        }
        menuAtm();

    }

    private static void enterMenu() {
        println(GREEN, "convert_money");
        println(GREEN, "take_money");
        //  println(GREEN,"fill_card");
        println(GREEN, "see_cash");
        println(RED, "quit");
        switch (getStr("?: ").toLowerCase(Locale.ROOT)) {
            case "take_money" -> takeMoney();
            // case "fill_money"->fillCard();
            case "see_cash" -> seeCash();
            case "quit" -> {
                println("good bye");
                return;
            }
        }
        enterMenu();
    }

    private static void convertMoney() {
        if (bakomatFunction()) return;
        println(BLUE, "USD");
        println(BLUE, "USB");
        switch (getStr("?: ").toUpperCase(Locale.ROOT)) {
            case "USD" -> usdConvert();
            case "USB" -> usbConvert();
            default -> println(RED, "error enter ");
        }
        write();
    }

    private static void write() {
        bankomats.removeIf(atms -> atm.getId().equals(atms.getId()));
        bankomats.add(atm);
        DB.writeFile(bankomats, "src/main/resources/db/bankomats.json");
        atm = null;

    }

    private static void usbConvert() {
        int sum = 0;
        while (true) {
            println("50");
            println("100");
            println("break: ");
            println("give: ");
            String str = getStr(" ?: ");
            if (str.equals("50")) sum += 50;
            takeEllikDollar();
            if (str.equals("100")) sum += 100;
            takeYuzDollar();
            if (str.equals("give") && sum >=50) {
                println(RED, "your money is-> " + sum*10000);
                if(!give(sum*10000, true))
                giveDollar(sum*10000,true);

                return;
            }
            if (str.equals("break")) {
                println(RED, "your money-> " + sum);
                return;
            }
        }

    }

    private static void takeYuzDollar() {
        atm.getCassetes().get(0).setCurrencyCount((short) (atm.getCassetes().get(2).getCurrencyCount() + 1));
    }

    private static void takeEllikDollar() {
        atm.getCassetes().get(1).setCurrencyCount((short) (atm.getCassetes().get(2).getCurrencyCount() + 1));

    }

    private static void usdConvert() {
        int sum = 0;
        while (true) {
            println("50000");
            println("100000");
            println("break: ");
            if (sum >=500000) {
                println("give: ");
            }
            String str = getStr(" ?: ");
            if (str.equals("50000")) {sum += 50000;
            takeEllik();}
            if (str.equals("100000")) {sum += 100000;
            takeYuz();}
            if (str.equals("give") && sum >=500000) {
                if (!giveDollar(sum, true)) {
                    println(RED, "your som is-> " + sum);
                    give(sum, true);
                } else {
                    println(RED, "your som is-> " + (sum % 500000));
                    give(sum % 500000, true);
                }
                return;
            }
            if (str.equals("break")) {
                println(RED, "your money-> " + sum);
                return;
            }
        }
    }

    private static void takeYuz() {
        atm.getCassetes().get(2).setCurrencyCount((short) (atm.getCassetes().get(2).getCurrencyCount() + 1));
    }

    private static void takeEllik() {
        atm.getCassetes().get(3).setCurrencyCount((short) (atm.getCassetes().get(2).getCurrencyCount() + 1));
    }


    private static void takeMoney() {
        int sum = 0;

        if (card.getCurrency().equals(Currency.UZS))
            while (true) {
                println("10000");
                println("20000");
                println("50000");
                println("100000");
                println("give: ");
                switch (getStr(" ?: ")) {
                    case "10000" -> sum += 10000;
                    case "20000" -> sum += 20000;
                    case "50000" -> sum += 50000;
                    case "100000" -> sum += 100000;
                    case "give" -> {
                        give(sum, false);
                        return;
                    }
                }
            }
        else {
            while (true) {
                println("50");
                println("100");
                println("give: ");
                switch (getStr(" ?: ")) {
                    case "50000" -> sum += 50;
                    case "100000" -> sum += 100;
                    case "give" -> {
                        giveDollar(sum, false);
                        return;
                    }
                }
            }
        }
    }

    private static Boolean giveDollar(int sum, boolean IsNotCard) {
        boolean haqiqat=false;
        if (!IsNotCard && card.getBalance() < sum) {
            print("you have mot  enough money ");
            return false;
        }
        int countBankomat = 0;
        int summa;
        if(IsNotCard ){
            sum=sum/10000;
        }
        summa= sum;
        for (Cassette cassete : atm.getCassetes()) {
            if (cassete.getCurrency().equals(Currency.USD))
                countBankomat += cassete.getKupyura().getCode() * cassete.getCurrencyCount();
        }
        if (countBankomat < sum) {
            print("we have mot  enough money ");
            return false;
        }

        int count = atm.getCassetes().get(0).getCurrencyCount();
        while (count > 0) {
            if (sum >= 100) {
                sum = sum - 100;
                println(CYAN, "💵 -> 100");
                haqiqat=true;
                count--;
            } else if (sum == 0) {
                atm.getCassetes().get(0).setCurrencyCount((short) count);
                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                break;
            }
        }

        count = atm.getCassetes().get(1).getCurrencyCount();
        while (count > 0) {
            if (sum >= 50) {
                sum = sum - 50;
                println(CYAN, "💲 -> 50");
                haqiqat = true;
                count--;
            } else if (sum == 0) {
                atm.getCassetes().get(0).setCurrencyCount((short) count);
                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                break;
            }
        }return haqiqat;


    }

    private static boolean give(int sum, boolean IsNotCard) {
        if (!IsNotCard && card.getBalance() < sum) {
            print("you have mot  enough money ");
            return false;
        }
        int countBankomat = 0;
        for (Cassette cassete : atm.getCassetes()) {
            if (cassete.getCurrency().equals(Currency.UZS))
                countBankomat += cassete.getKupyura().getCode() * cassete.getCurrencyCount();
        }
        if (countBankomat < sum) {
            print("you have mot  enough money ");
            return false;
        }
        int summa = sum;

        short count = atm.getCassetes().get(2).getCurrencyCount();
        while (count > 0) {
            if (sum >= 100000) {
                sum = sum - 100000;
                println(CYAN, "💸 -> 100000");
                count--;
                atm.getCassetes().get(2).setCurrencyCount( count);
            } else if (sum == 0) {
                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                System.out.println(1);
                break;
            }
        }


        count = atm.getCassetes().get(3).getCurrencyCount();
        while (count > 0) {
            if (sum >= 50000) {
                sum = sum - 50000;
                println(CYAN, "💸 -> 50000");
                count--;
                atm.getCassetes().get(3).setCurrencyCount(count);
            } else if (sum == 0) {


                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                System.out.println(2);
                break;
            }
        }

        count = atm.getCassetes().get(4).getCurrencyCount();
        while (count > 0) {
            if (sum >= 20000) {
                sum = sum - 20000;
                println(CYAN, "💸 -> 20000");
                count--;
                atm.getCassetes().get(4).setCurrencyCount(count);
            } else if (sum == 0) {


                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                System.out.println(3);
                break;
            }
        }
        count = atm.getCassetes().get(4).getCurrencyCount();
        while (count > 0) {
            if (sum >= 10000) {
                sum = sum - 10000;
                println(CYAN, "💸 -> 10000");
                count--;
                atm.getCassetes().get(5).setCurrencyCount(count);
            } else if (sum == 0) {


                if (!IsNotCard) card.setBalance(card.getBalance() - summa);
                println("you succesfully taked");
                return true;
            } else {
                System.out.println(4);
                break;
            }
        }
        return true;

    }


    private static void seeCash() {
        println(GREEN, "⚖️"+card.getBalance());
    }

    private static void writeAll() {
        getCards().removeIf(cards -> card.getPan().equals(cards.getPan()));
        getCards().add(card);
        DB.writeFile(getCards(), "src/main/resources/db/cards.json");
        write();
        card = null;
    }

}
