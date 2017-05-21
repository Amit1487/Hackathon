package bhartiairtel.themehackathon.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by B0089798 on 20 / May / 2017 , 7:28 PM.
 * bhartiairtel.themehackathon.register
 * Hackathon
 */
public class RegisterRequest {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;
    @SerializedName("mpin")
    @Expose
    private String mpin;
    @SerializedName("mailid")
    @Expose
    private String mailid;
    @SerializedName("type")
    @Expose
    private String type;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobilenumber() {
        return mobilenumber;
    }
    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
    public String getMpin() {
        return mpin;
    }
    public void setMpin(String mpin) {
        this.mpin = mpin;
    }
    public String getMailid() {
        return mailid;
    }
    public void setMailid(String mailid) {
        this.mailid = mailid;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
