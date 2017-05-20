
package bhartiairtel.themehackathon.login;

public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onSuccess(Object result);

        void onFailure(String errorMsg);
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
