package bhartiairtel.themehackathon.echeck;

import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ECheckInteractorImpl implements ECheckInteractor {

    @Override
    public void createECheck(String frommobile, String payeemobile, String payeename, String idprooftype, String idprofnumber, String amount, String username, String mPin, final OnECheckCreatedListener listener) {

        CreateChequeRequestBean createChequeRequestBean = new CreateChequeRequestBean();
        createChequeRequestBean.setFrommobile(frommobile);
        createChequeRequestBean.setPayeemobile(payeemobile);
        createChequeRequestBean.setPayeename(payeename);
        createChequeRequestBean.setIdprooftype(idprooftype);
        createChequeRequestBean.setIdprofnumber(idprofnumber);
        createChequeRequestBean.setAmount(Long.parseLong(amount));
        createChequeRequestBean.setUsername(username);
        createChequeRequestBean.setMpin(mPin);


        Call call = APIClient.getClient().create(APIInterface.class).createCheck(createChequeRequestBean);
        call.enqueue(new Callback<ECheckResponse>() {

            @Override
            public void onResponse(Call<ECheckResponse> call, Response<ECheckResponse> response) {

                ECheckResponse commonRes = response.body();

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
            public void onFailure(Call<ECheckResponse> call, Throwable t) {
                call.cancel();
                listener.onFailure(t.getMessage());
            }
        });
    }


}
