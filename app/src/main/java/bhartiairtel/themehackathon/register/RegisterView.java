
package bhartiairtel.themehackathon.register;

public interface RegisterView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
