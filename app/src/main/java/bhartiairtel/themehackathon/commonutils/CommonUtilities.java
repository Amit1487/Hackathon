package bhartiairtel.themehackathon.commonutils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtilities {


    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        return password.length() > 5;
    }

    static String REGEX_PHONE_NUMBER = "^[7-9][0-9]{9}$";

    /**
     * return true if phone number is valid
     *
     * @param number
     * @return
     */
    public static boolean isValidPhoneNumber(String number) {
        boolean isValid = false;
        if (number != null && number.matches(REGEX_PHONE_NUMBER)) {
            return true;
        }
        return isValid;

    }

}
