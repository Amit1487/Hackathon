package bhartiairtel.themehackathon.login;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.LoginResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call call = APIClient.getClient().create(APIInterface.class).loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();

                MessageBean msgBean = loginResponse.getMessageBean();
                if(msgBean.getStatuscode() == 200){
                    listener.onSuccess();
                }else{

                }

            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();
                listener.onFailure();
            }
        });
    }
}
