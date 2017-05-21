package bhartiairtel.themehackathon.encash;

/**
 * Created by B0089798 on 21 / May / 2017 , 3:32 AM.
 * bhartiairtel.themehackathon.encash
 * Hackathon
 */

public class EnCashChequeRequest {
    String username;//":"8698015062",
    String mpin;//":"1111",
    String chequenumber;//":"Cheque3822"

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMpin() {
        return mpin;
    }

    public void setMpin(String mpin) {
        this.mpin = mpin;
    }

    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
    }
}
