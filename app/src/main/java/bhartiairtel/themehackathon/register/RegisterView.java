
package bhartiairtel.themehackathon.register;

public interface RegisterView {
    void showProgress();

    void hideProgress();

    void setUsernameError(boolean error);

    void setMobileNumberError(boolean error);

    void setEmailError(boolean error);

    void setPasswordError(boolean error);

    void onSuccess();

    void onError();
}
