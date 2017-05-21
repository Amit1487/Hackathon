package bhartiairtel.themehackathon.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.login.LoginActivity;
import bhartiairtel.themehackathon.login.LoginPresenter;
import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.main.MainActivity;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements RegisterView, View.OnClickListener {

    private RegisterPresenter presenter;
    private EditText mEtUserName, mEtMobileNumber, mEtEmailId, mEtPassword, mEtCnfPassword;

    TextInputLayout mTilUsernameWrapper, mTilMobNumWrapper, mTilEmailWrapper, mTilPwdWrapper, mTilCnfPwdWrapper;
    Spinner mSpinnerUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTilUsernameWrapper = (TextInputLayout) findViewById(R.id.til_uname);
        mTilUsernameWrapper.setHint(getString(R.string.hint_usr_name));
        mTilMobNumWrapper = (TextInputLayout) findViewById(R.id.til_mob);
        mTilMobNumWrapper.setHint(getString(R.string.hint_mob_num));

        mTilEmailWrapper = (TextInputLayout) findViewById(R.id.til_email);
        mTilEmailWrapper.setHint(getString(R.string.hint_email));
        mTilPwdWrapper = (TextInputLayout) findViewById(R.id.til_pwd);
        mTilPwdWrapper.setHint(getString(R.string.hint_pwd));

        mTilCnfPwdWrapper = (TextInputLayout) findViewById(R.id.til_confirm_pwd);
        mTilCnfPwdWrapper.setHint(getString(R.string.hint_cnf_pwd));

        mEtUserName = (EditText) findViewById(R.id.et_mob);
        mEtMobileNumber = (EditText) findViewById(R.id.et_mpin);
        mEtEmailId = (EditText) findViewById(R.id.et_mpin);
        mEtPassword = (EditText) findViewById(R.id.et_mpin);
        mEtCnfPassword = (EditText) findViewById(R.id.et_mpin);
        mSpinnerUserType = (Spinner) findViewById(R.id.spn_user_type);
        findViewById(R.id.btn_submit).setOnClickListener(this);

        presenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
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
    public void setUsernameError(boolean error) {
        if (error)
            mTilUsernameWrapper.setError(getString(R.string.name_error));
        else
            mTilUsernameWrapper.setErrorEnabled(false);
    }

    @Override
    public void setPasswordError(boolean error) {
        if (error)
            mTilCnfPwdWrapper.setError(getString(R.string.password_error));
        else
            mTilCnfPwdWrapper.setErrorEnabled(false);
    }

    @Override
    public void setEmailError(boolean error) {
        if (error)
            mTilEmailWrapper.setError(getString(R.string.email_error));
        else
            mTilEmailWrapper.setErrorEnabled(false);
    }

    @Override
    public void setMobileNumberError(boolean error) {
        if (error)
            mTilMobNumWrapper.setError(getString(R.string.username_error));
        else
            mTilMobNumWrapper.setErrorEnabled(false);
    }

    @Override
    public void onSuccess() {
        requestUsersDetail();
//        startActivity(new Intent(this, NavigationDrawerActivity.class));
//        finish();
    }

    private void requestUsersDetail() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(mTilUsernameWrapper.getEditText().getText().toString());

        Call call = APIClient.getClient().create(APIInterface.class).getUserDetails(loginRequest);
        call.enqueue(new Callback<UserDetailsResponse>() {

                         @Override
                         public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {

                             UserDetailsResponse commonResponse = response.body();

                             MessageBean msgBean = commonResponse.getMessageBean();
                             if (msgBean.getStatuscode() == 200) {
                                 //display UI
                                 onUseretail((GetUserDetailsResponseBean) commonResponse.getResult());
                             } else {

                                 String msg = "Some Issues";
                                 try {
                                     msg = (String) msgBean.getMessage();
                                 } catch (ClassCastException e) {

                                 } catch (Exception e) {

                                 }

                                 new AlertDialog(RegisterActivity.this, AlertDialog.ERROR_TYPE)
                                         .setTitleText("Oops...")
                                         .setContentText(msg)
                                         .show();
                             }
                         }

                         @Override
                         public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                             call.cancel();
                             new AlertDialog(RegisterActivity.this, AlertDialog.ERROR_TYPE)
                                     .setTitleText("Oops...")
                                     .setContentText("Something went wrong.")
                                     .show();
                         }

                     }

        );
    }

    private void onUseretail(GetUserDetailsResponseBean result) {
        Intent in = new Intent(this, LoginActivity.class);
        //in.putExtra("result", result);
//        in.putExtra("user_name", mTilUsernameWrapper.getEditText().getText().toString());
//        in.putExtra("mpin", mTilPasswordWrapper.getEditText().getText().toString());
        startActivity(in);
        finish();
    }


    @Override
    public void onClick(View v) {
        presenter.validateInput(mTilUsernameWrapper.getEditText().getText().toString(), mTilMobNumWrapper.getEditText().getText().toString(), mTilEmailWrapper.getEditText().getText().toString(), mSpinnerUserType.getSelectedItem().toString(), mTilPwdWrapper.getEditText().getText().toString(), mTilCnfPwdWrapper.getEditText().getText().toString());
    }

    @Override
    public void onError() {
        new AlertDialog(this, AlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show();
    }
}
