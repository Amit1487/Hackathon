package bhartiairtel.themehackathon.addmoney;

public interface AddMoneyPresenter {
    void validateInput(String userName, String mPin, String amount, String account, String bankName);

    void onDestroy();
}
