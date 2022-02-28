package Jafar.ibook.services.filesystems;

import com.google.gson.reflect.TypeToken;
import Jafar.ibook.configs.Session;
import Jafar.ibook.models.atm.ATM;
import Jafar.ibook.models.auth.AuthUser;
import Jafar.ibook.models.branch.Branch;
import Jafar.ibook.models.card.Card;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DB implements BaseDB {

    private static final List<AuthUser> users = loadUsers(getUsers());
    private static final List<Session> sessionsList =loadSession (getSessionsList());
    private static final List<Branch> branches = loadBranches(getBranches());
    private static final List<Card> cards = loadCards(getCards());
    public static final List<ATM> bankomats = loadATMs(getBankomats());

    public static List<AuthUser> loadUsers(List<AuthUser> list) {
        if (Objects.isNull(list)) {
            try (FileReader reader = new FileReader(pathPre+"users.json");
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String jsonDATA = bufferedReader.lines().collect(Collectors.joining());
                list = GSON.fromJson(jsonDATA, new TypeToken<List<AuthUser>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Session> loadSession(List<Session> list) {
        if (Objects.isNull(list)) {
            try (FileReader reader = new FileReader(pathPre+"session.json");
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String jsonDATA = bufferedReader.lines().collect(Collectors.joining());
                list = GSON.fromJson(jsonDATA, new TypeToken<List<Session>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Branch> loadBranches(List<Branch> list) {
        if (Objects.isNull(list)) {
            try (FileReader reader = new FileReader(pathPre+"branches.json");
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String jsonDATA = bufferedReader.lines().collect(Collectors.joining());
                list = GSON.fromJson(jsonDATA, new TypeToken<List<Branch>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Card> loadCards(List<Card> list) {
        if (Objects.isNull(list)) {
            try (FileReader reader = new FileReader(pathPre+"cards.json");
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String jsonDATA = bufferedReader.lines().collect(Collectors.joining());
                list = GSON.fromJson(jsonDATA, new TypeToken<List<Card>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<ATM> loadATMs(List<ATM> list) {
        if (Objects.isNull(list)) {
            try (FileReader reader = new FileReader(pathPre+ "bankomats.json");
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String jsonDATA = bufferedReader.lines().collect(Collectors.joining());
                list = GSON.fromJson(jsonDATA, new TypeToken<List<ATM>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static <T> void writeFile(List<T> list, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(GSON.toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AuthUser> getUsers() {
        return users;
    }
    public static List<Card> getCards() {
        return cards;
    }

    public static List<Session> getSessionsList() {
        return sessionsList;
    }

    public static List<Branch> getBranches() {
        return branches;
    }

    public static List<ATM> getBankomats() {
        return bankomats;
    }

}
