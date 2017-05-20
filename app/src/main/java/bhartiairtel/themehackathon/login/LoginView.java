
package bhartiairtel.themehackathon.login;

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError(boolean isError);

    void setPasswordError(boolean isError);

    void onSuccess(Object result);

    void onError(String errMsg);
}
