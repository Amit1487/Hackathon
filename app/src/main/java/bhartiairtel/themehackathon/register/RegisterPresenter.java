
package bhartiairtel.themehackathon.register;

public interface RegisterPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
