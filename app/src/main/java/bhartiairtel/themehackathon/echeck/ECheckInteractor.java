
package bhartiairtel.themehackathon.echeck;

public interface ECheckInteractor {

    interface OnECheckCreatedListener {

        void onSuccess(CheckDetailsBody result);

        void onFailure(String errorMsg);
    }

    void createECheck(String frommobile, String payeemobile, String payeename, String idprooftype, String idprofnumber, String amount,String username, String mPin, OnECheckCreatedListener listener);

}
