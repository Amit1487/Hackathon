package bhartiairtel.themehackathon.pojo;

/**
 * Created by B0089737 on 5/20/2017.
 */

import java.util.List;

public class GetUserDetailsResponseBean {
    private String mobilenumber;
    private String name;
    private String mailid;
    private String amount;
    private List<String> roles;

    public String getMobilenumber() {
        return mobilenumber;
    }
    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailid() {
        return mailid;
    }
    public void setMailid(String mailid) {
        this.mailid = mailid;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}