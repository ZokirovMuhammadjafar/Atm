package Jafar.ibook.services.filesystems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public interface BaseDB {
    String pathPre = "src/main/resources/db/";
    Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

}
