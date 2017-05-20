package bhartiairtel.themehackathon.pojo;

/**
 * Created by B0089737 on 5/20/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class GetUserDetailsResponseBean implements Serializable{
    private String mobilenumber;
    private String name;
    private String mailid;
    private String amount;
    private List<String> roles;

    /*protected GetUserDetailsResponseBean(Parcel in) {
        mobilenumber = in.readString();
        name = in.readString();
        mailid = in.readString();
        amount = in.readString();
        roles = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobilenumber);
        dest.writeString(name);
        dest.writeString(mailid);
        dest.writeString(amount);
        dest.writeStringList(roles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GetUserDetailsResponseBean> CREATOR = new Creator<GetUserDetailsResponseBean>() {
        @Override
        public GetUserDetailsResponseBean createFromParcel(Parcel in) {
            return new GetUserDetailsResponseBean(in);
        }

        @Override
        public GetUserDetailsResponseBean[] newArray(int size) {
            return new GetUserDetailsResponseBean[size];
        }
    };
*/
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