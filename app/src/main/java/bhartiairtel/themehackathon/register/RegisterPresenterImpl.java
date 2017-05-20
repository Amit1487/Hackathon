
package bhartiairtel.themehackathon.register;

import bhartiairtel.themehackathon.commonutils.CommonUtilities;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnRegisterFinishedListener {

    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView loginView) {
        this.registerView = loginView;
        this.registerInteractor = new RegisterInteractorImpl();
    }

    @Override
    public void validateInput(String username,String mobileNun,String emailId,String userType, String password, String cnfPassword) {
        if (registerView != null) {
            registerView.showProgress();
        }

        if (username.length()>3){
            registerView.setUsernameError(false);
            if(CommonUtilities.isValidPhoneNumber(mobileNun)){
                registerView.setMobileNumberError(false);

                if(CommonUtilities.validateEmail(emailId)){
                    registerView.setEmailError(false);
                    if(password.length()>0) {
                        if (password.equals(cnfPassword)) {
                            registerView.setPasswordError(false);

                            registerInteractor.registerUser(username,mobileNun,emailId,userType, password, cnfPassword, this);

                        } else {
                            registerView.setPasswordError(true);
                        }
                    }else{
                        registerView.setPasswordError(true);
                    }
                }else{
                    registerView.setEmailError(true);
                }

            }else{
                registerView.setMobileNumberError(true);
            }
        }else{
            registerView.setUsernameError(true);
        }


    }

    @Override
    public void onDestroy() {
        registerView = null;
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.navigateToHome();
        }
    }

    @Override
    public void onFailure() {
        if (registerView != null) {
            registerView.onError();
        }
    }

}
