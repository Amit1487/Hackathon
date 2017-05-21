package bhartiairtel.themehackathon.addmoney;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddMoneyInteractorImpl implements AddMoneyInteractor {

    @Override
    public void addMoney(String userName, String mPin, String amount, String account, String bankName, final OnAddMoneyFinishedListener listener) {

        AddMoneyRequestBean addMoneyRequest = new AddMoneyRequestBean();
        addMoneyRequest.setUsername(userName);
        addMoneyRequest.setMpin(mPin);
        addMoneyRequest.setAmount(amount);


        Call call = APIClient.getClient().create(APIInterface.class).addMoney(addMoneyRequest);
        call.enqueue(new Callback<CommonResponse>() {

            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                CommonResponse commonRes = response.body();

                MessageBean msgBean = commonRes.getMessageBean();
                if (msgBean.getStatuscode() == 200) {
                    listener.onSuccess((String) msgBean.getMessage());
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
