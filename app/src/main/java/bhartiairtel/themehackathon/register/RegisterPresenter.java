
package bhartiairtel.themehackathon.register;

public interface RegisterPresenter {
    void validateInput(String username,String mobileNun,String emailId,String userType, String password, String cnfPassword);

    void onDestroy();
}
