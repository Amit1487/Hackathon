
package bhartiairtel.themehackathon.login;

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void onError();
}
