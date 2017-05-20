/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
