
package bhartiairtel.themehackathon.register;

public interface RegisterInteractor {

    interface OnRegisterFinishedListener {

        void onSuccess();

        void onFailure(String error);
    }

    void registerUser(String username,String mobileNun,String emailId,String userType, String password, String cnfPassword, OnRegisterFinishedListener listener);

}
