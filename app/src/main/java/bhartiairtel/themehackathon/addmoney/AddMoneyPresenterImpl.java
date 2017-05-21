
package bhartiairtel.themehackathon.addmoney;

public class AddMoneyPresenterImpl implements AddMoneyPresenter, AddMoneyInteractor.OnAddMoneyFinishedListener {

    private AddMoneyView addMoneyView;
    private AddMoneyInteractor addMoneyInteractor;

    public AddMoneyPresenterImpl(AddMoneyView addMoneyView) {
        this.addMoneyView = addMoneyView;
        this.addMoneyInteractor = new AddMoneyInteractorImpl();
    }


    @Override
    public void validateInput(String userName, String mPin, String amount, String account, String bankName) {
        if (addMoneyView != null) {
            addMoneyView.showProgress();
        }
        if (amount.trim().length() == 0) {
            amount = "0";
        }
        if (Integer.parseInt(amount) >= 10) {
            addMoneyView.setAmountError(false);

            if (account.length() >= 10) {
                addMoneyView.setAccountError(false);

                if (bankName.length() > 0) {
                    addMoneyView.setBankError(false);
                    addMoneyView.showProgress();
                    addMoneyInteractor.addMoney(userName, mPin, amount, account, bankName, this);

                } else {
                    addMoneyView.setBankError(true);
                }

            } else {
                addMoneyView.setAccountError(true);
            }

        } else {
            addMoneyView.setAmountError(true);
        }

    }

    @Override
    public void onDestroy() {
        addMoneyView = null;
    }

    @Override
    public void onSuccess(String result) {
        if (addMoneyView != null) {
            addMoneyView.hideProgress();
            addMoneyView.onSuccess(result);
        }
    }

    @Override
    public void onFailure(String errMsg) {
        if (addMoneyView != null) {
            addMoneyView.hideProgress();
            addMoneyView.onError(errMsg);
        }
    }
}
