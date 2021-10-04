/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.scene;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class Validation {

    public static boolean checkPassword(String pass) {
        return pass.length() >= 6 && pass.length() <= 30;
    }

    //  Name is invalid if it changed after removing accents, or it has blank space
    public static boolean checkUsername(String username) {
        if (!removeAccent(username).equalsIgnoreCase(username) || username.contains(" ")) {
            return false;
        }
        return username.length() > 0 && username.length() <= 15;
    }

    //http://sinhviencntt.blogspot.com/2015/01/code-java-chuyen-oi-tieng-viet-co-dau.html
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static boolean checkInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean checkName(String name) {
        if (!removeAccent(name).equalsIgnoreCase(name) || name.contains(" ")) {
            return false;
        }
        return name.length() > 0 && name.length() <= 15;
    }
}
