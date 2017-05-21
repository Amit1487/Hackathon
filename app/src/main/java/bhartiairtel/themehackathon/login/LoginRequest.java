package bhartiairtel.themehackathon.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by B0089798 on 20 / May / 2017 , 6:37 PM.
 * bhartiairtel.themehackathon.login
 * Hackathon
 */

public class LoginRequest {
    @SerializedName("username")
    @Expose
    String username;
    @SerializedName("password")
    @Expose
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
