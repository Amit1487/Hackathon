
package bhartiairtel.themehackathon.echeck;

public class ECheckPresenterImpl implements ECheckPresenter, ECheckInteractor.OnECheckCreatedListener {

    private ECheckView eCheckView;
    private ECheckInteractor eCheckInteractor;

    public ECheckPresenterImpl(ECheckView ECheckView) {
        this.eCheckView = ECheckView;
        this.eCheckInteractor = new ECheckInteractorImpl();
    }


    @Override
    public void validateInput(String frommobile, String payeemobile, String payeename, String idprooftype, String idprofnumber, String amount, String username, String mPin) {
        if (eCheckView != null) {
            eCheckView.showProgress();
        }

        eCheckInteractor.createECheck(frommobile, payeemobile, payeename, idprooftype, idprofnumber, amount, username, mPin, this);
    }

    @Override
    public void onDestroy() {
        eCheckView = null;
    }

    @Override
    public void onSuccess(CheckDetailsBody result) {
        if (eCheckView != null) {

            String res = result.getChequenumber();

            eCheckView.onSuccess(res);
        }
    }

    @Override
    public void onFailure(String errMsg) {
        if (eCheckView != null) {
            eCheckView.onError(errMsg);
        }
    }
}
