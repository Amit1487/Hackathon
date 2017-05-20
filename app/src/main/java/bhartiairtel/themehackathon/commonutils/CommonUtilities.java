package bhartiairtel.themehackathon.commonutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bhartiairtel.themehackathon.R;

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
        return password.length() > 3;
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

    public static void createAlert(Context context, String msg, final OnMpinListener listener) {
        {


            final Dialog mDialog = new Dialog(context, android.R.style.Theme_Dialog);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setTitle(null);
            mDialog.getWindow().getAttributes().windowAnimations = R.style.grow_anim;
            mDialog.setContentView(R.layout.dialog_mpin);
            ((TextView) mDialog.findViewById(R.id.msg)).setText(msg);
            final EditText mEtPin = (EditText) mDialog.findViewById(R.id.et_pin);
            mDialog.findViewById(R.id.postive).setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if (mEtPin.getText().toString().length() > 3) {
                                mDialog.dismiss();
                                listener.onEntered(mEtPin.getText().toString());
//                                return mEtPin.getText().toString();
                            } else {
                                mEtPin.setError("in valid mpin");
                            }


                        }
                    });
            mDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.show();
        /*return mDialog;*/


        }
    }


    public interface OnMpinListener {
        public void onEntered(String mPin);
    }

    OnMpinListener listener;

    public void setOnMPinListener(OnMpinListener listener) {
        this.listener = listener;
    }

}
