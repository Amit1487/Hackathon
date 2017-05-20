package bhartiairtel.themehackathon.login;

import com.google.gson.Gson;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call call = APIClient.getClient().create(APIInterface.class).loginUser(new Gson().toJson(loginRequest));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                response.body(); == is actuall response
                //here check response and then pass it to listener
                listener.onSuccess();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();

                listener.onFailure();
            }
        });
    }
}
