package com.tip.lunchbox.utilities;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean passwordValidator(String password) {
        if (password.isEmpty()) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        if (!hasCap(password)) {
            return false;
        }
        if (!hasNum(password)) {
            return false;
        }
        if (!hasSpecial(password)) {
            return false;
        }

        return true;
    }

    public static boolean usernameValidator(String userName) {
        if (userName.isEmpty()) {
            return false;
        }
        if (userName.startsWith(" ")) {
            return false;
        }
        if (userName.endsWith(" ")) {
            return false;
        }
        return true;
    }

    private static Boolean hasCap(String password) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcherCap = pattern.matcher(password);
        return matcherCap.find();
    }

    private static Boolean hasNum(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcherNum = pattern.matcher(password);
        return matcherNum.find();
    }

    private static Boolean hasSpecial(String password) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcherSpecial = pattern.matcher(password);
        return matcherSpecial.find();
    }
}
