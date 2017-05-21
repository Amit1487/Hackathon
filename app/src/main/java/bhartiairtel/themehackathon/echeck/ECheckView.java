package bhartiairtel.themehackathon.echeck;

/**
 * Created by B0089798 on 20 / May / 2017 , 10:16 PM.
 * bhartiairtel.themehackathon.addmoney
 * Hackathon
 */

public interface ECheckView {

    void showProgress();

    void hideProgress();

    void setNameError(boolean isError);

    void setMobNumberError(boolean isError);

    void setAmountError(boolean isError);

    void setDocumentError(boolean isError);

    void onSuccess(String result);

    void onError(String err);
}
