
package bhartiairtel.themehackathon.addmoney;

public interface AddMoneyInteractor {

    interface OnAddMoneyFinishedListener {

        void onSuccess(String result);

        void onFailure(String errorMsg);
    }

    void addMoney(String userName, String mPin, String amount, String account, String bankName, OnAddMoneyFinishedListener listener);

}
