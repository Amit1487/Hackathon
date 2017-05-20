package bhartiairtel.themehackathon.login;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call call = APIClient.getClient().create(APIInterface.class).loginUser(loginRequest);
        call.enqueue(new Callback<CommonResponse>() {

            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                CommonResponse commonRes = response.body();

                MessageBean msgBean = commonRes.getMessageBean();
                if (msgBean.getStatuscode() == 200) {
                    listener.onSuccess(commonRes.getResult());
                } else {
                    try {
                        listener.onFailure((String) msgBean.getMessage());
                    } catch (ClassCastException e) {
                        listener.onFailure("Failed");
                    } catch (Exception e) {
                        listener.onFailure("Something went wrong");
                    }
                }
            }


            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                call.cancel();
                listener.onFailure(t.getMessage());
            }
        });
    }
}
