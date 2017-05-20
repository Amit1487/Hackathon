package bhartiairtel.themehackathon.login;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

        Call call = APIClient.getClient().create(APIInterface.class).doGetLoginResources();
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //here check response and then pass it to listener

            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
