package bhartiairtel.themehackathon.pojo;

/**
 * Created by B0089737 on 5/21/2017.
 */

public class UserDetailsResponse {

    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private GetUserDetailsResponseBean result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public GetUserDetailsResponseBean getResult() {
        return result;
    }

    public void setResult(GetUserDetailsResponseBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }
}
