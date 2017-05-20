
package bhartiairtel.themehackathon.register;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnLoginFinishedListener {

    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView loginView) {
        this.registerView = loginView;
        this.registerInteractor = new RegisterInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (registerView != null) {
            registerView.showProgress();
        }

        registerInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }

    @Override
    public void onUsernameError() {
        if (registerView != null) {
            registerView.setUsernameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (registerView != null) {
            registerView.setPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.navigateToHome();
        }
    }
}
