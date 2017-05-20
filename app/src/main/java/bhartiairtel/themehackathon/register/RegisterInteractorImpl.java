package bhartiairtel.themehackathon.register;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterInteractorImpl implements RegisterInteractor {

    @Override
    public void registerUser(final String username, final String mobileNun, final String emailId, final String userType, final String password, final String cnfPassword, final OnRegisterFinishedListener listener) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setMobileNun(mobileNun);
        registerRequest.setEmailId(emailId);
        registerRequest.setUserType(userType);
        registerRequest.setPassword(password);
        registerRequest.setCnfPassword(cnfPassword);

        Call call = APIClient.getClient().create(APIInterface.class).createUser(registerRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
//                response.body(); == is actuall response
                //here check response and then pass it to listener
                listener.onSuccess();

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                call.cancel();

                listener.onFailure();
            }
        });
    }
}
