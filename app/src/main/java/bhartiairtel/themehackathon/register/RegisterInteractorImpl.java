package bhartiairtel.themehackathon.register;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.LoginResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterInteractorImpl implements RegisterInteractor {

    @Override
    public void registerUser(final String username, final String mobileNun, final String emailId, final String userType, final String password, final String cnfPassword, final OnRegisterFinishedListener listener) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(username);
        registerRequest.setMobilenumber(mobileNun);
        registerRequest.setMailid(emailId);
        registerRequest.setType(userType);
        registerRequest.setMpin(password);
        //registerRequest.setCnfPassword(cnfPassword);

        Call call = APIClient.getClient().create(APIInterface.class).createUser(registerRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
//                response.body(); == is actuall response
                //here check response and then pass it to listener
                //listener.onSuccess();
                CommonResponse loginResponse = response.body();

                MessageBean msgBean = loginResponse.getMessageBean();
                if(msgBean.getStatuscode() == 200){
                    listener.onSuccess();
                }else{

                }

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                call.cancel();

                listener.onFailure();
            }
        });
    }
}
