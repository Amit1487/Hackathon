package bhartiairtel.themehackathon.addmoney;

/**
 * Created by B0089798 on 20 / May / 2017 , 10:16 PM.
 * bhartiairtel.themehackathon.addmoney
 * Hackathon
 */

public interface AddMoneyView {

    void showProgress();

    void hideProgress();

    void setAmountError(boolean isError);

    void setAccountError(boolean isError);

    void setBankError(boolean isError);

    void onSuccess(String result);

    void onError(String err);
}
