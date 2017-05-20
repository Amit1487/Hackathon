
package bhartiairtel.themehackathon.login;

import bhartiairtel.themehackathon.commonutils.CommonUtilities;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        if (CommonUtilities.isValidPhoneNumber(username)) {
            loginView.setUsernameError(false);
            if (CommonUtilities.validatePassword(password)) {
                loginView.setPasswordError(false);
//                loginView.navigateToHome();
                loginInteractor.login(username, password, this);
            } else {
                loginView.setPasswordError(true);
            }
        } else {
            loginView.setUsernameError(true);
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onSuccess(Object result) {
        if (loginView != null) {
            loginView.navigateToHome(result);
        }
    }

    @Override
    public void onFailure(String errMsg) {
        if (loginView != null) {
            loginView.onError(errMsg);
        }
    }
}
