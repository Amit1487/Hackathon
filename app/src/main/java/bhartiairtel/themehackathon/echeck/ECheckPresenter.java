package bhartiairtel.themehackathon.echeck;

public interface ECheckPresenter {
    void validateInput(String frommobile, String payeemobile, String payeename, String idprooftype, String idprofnumber, String amount,String username, String mPin);

    void onDestroy();
}
