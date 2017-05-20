
package bhartiairtel.themehackathon.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;


import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.commonutils.CommonUtilities;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import bhartiairtel.themehackathon.register.RegisterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    //    private ProgressBar progressBar;
    private EditText mEtMobileNumber;
    private EditText mEtMPin;
    private LoginPresenter mPresenter;
    TextInputLayout mTilPasswordWrapper;
    TextInputLayout mTilUsernameWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        progressBar = (ProgressBar) findViewById(R.id.progress);

        mTilUsernameWrapper = (TextInputLayout) findViewById(R.id.til_mob__wrapper);
        mTilUsernameWrapper.setHint(getString(R.string.hint_mob_num));
        mTilPasswordWrapper = (TextInputLayout) findViewById(R.id.til_mpin_wrapper);
        mTilPasswordWrapper.setHint(getString(R.string.hint_pass));


        mEtMobileNumber = (EditText) findViewById(R.id.et_mob);
        mEtMPin = (EditText) findViewById(R.id.et_mpin);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_reg_user).setOnClickListener(this);

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
    public void onSuccess(Object result) {
        requestUsersDetail();
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

                                 new AlertDialog(LoginActivity.this, AlertDialog.ERROR_TYPE)
                                         .setTitleText("Oops...")
                                         .setContentText(msg)
                                         .show();
                             }
                         }

                         @Override
                         public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                             call.cancel();
                             new AlertDialog(LoginActivity.this, AlertDialog.ERROR_TYPE)
                                     .setTitleText("Oops...")
                                     .setContentText("Something went wrong.")
                                     .show();
                         }

                     }

        );
    }

    private void onUseretail(GetUserDetailsResponseBean result) {
        Intent in = new Intent(this, NavigationDrawerActivity.class);
        in.putExtra("result", result);
        startActivity(in);
        finish();
    }


    @Override
    public void onError(String errMsg) {
        new AlertDialog(this, AlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(errMsg)
                .show();
    }

    @Override
    public void onClick(View v) {

        CommonUtilities.hideKeyboard(this, v);

        switch (v.getId()) {
            case R.id.btn_submit:
                mPresenter.validateCredentials(mTilUsernameWrapper.getEditText().getText().toString(), mTilPasswordWrapper.getEditText().getText().toString());
                break;
            case R.id.btn_reg_user:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }

    }
}