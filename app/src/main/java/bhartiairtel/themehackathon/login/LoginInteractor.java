
package bhartiairtel.themehackathon.login;

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onFailure();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
