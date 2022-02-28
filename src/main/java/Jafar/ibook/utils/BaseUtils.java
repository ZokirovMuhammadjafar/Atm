package Jafar.ibook.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class BaseUtils {
    public static String genID() {
        return System.nanoTime() + RandomStringUtils.random(26, true, true);
    }

    public static String generateCardPan() {
        return (""+System.currentTimeMillis()).substring(0,12);
    }
}
