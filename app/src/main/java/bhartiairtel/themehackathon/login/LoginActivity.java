
package bhartiairtel.themehackathon.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;


import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.commonutils.CommonUtilities;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private LoginPresenter mPresenter;
    TextInputLayout mTilPasswordWrapper;
    TextInputLayout mTilUsernameWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        mTilUsernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        mTilUsernameWrapper.setHint(getString(R.string.hint_usr_name));
        mTilPasswordWrapper = (TextInputLayout) findViewById(R.id.passWrapper);
        mTilPasswordWrapper.setHint(getString(R.string.hint_pass));


        mEtUsername = (EditText) findViewById(R.id.username);
        mEtPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError(boolean isError) {
        if (isError)
        mTilUsernameWrapper.setError(getString(R.string.username_error));
        else
            mTilUsernameWrapper.setErrorEnabled(false);
    }

    @Override
    public void setPasswordError(boolean isError) {
        if (isError)
        mTilPasswordWrapper.setError(getString(R.string.password_error));
        else
            mTilPasswordWrapper.setErrorEnabled(false);
    }

    @Override
    public void navigateToHome() {




        startActivity(new Intent(this, NavigationDrawerActivity.class));
        finish();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onClick(View v) {
        CommonUtilities.hideKeyboard(this, v);

//        new AlertDialog(this).setTitleText(username)
//                .setContentText(password)
//                .show();
        mPresenter.validateCredentials(mTilUsernameWrapper.getEditText().getText().toString(), mTilPasswordWrapper.getEditText().getText().toString());
    }
}