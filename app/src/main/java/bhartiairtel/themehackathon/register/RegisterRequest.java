package bhartiairtel.themehackathon.register;

/**
 * Created by B0089798 on 20 / May / 2017 , 7:28 PM.
 * bhartiairtel.themehackathon.register
 * Hackathon
 */
public class RegisterRequest {

    String username;
    String mobileNun;
    String emailId;
    String userType;
    String password;
    String cnfPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNun() {
        return mobileNun;
    }

    public void setMobileNun(String mobileNun) {
        this.mobileNun = mobileNun;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnfPassword() {
        return cnfPassword;
    }

    public void setCnfPassword(String cnfPassword) {
        this.cnfPassword = cnfPassword;
    }
}
