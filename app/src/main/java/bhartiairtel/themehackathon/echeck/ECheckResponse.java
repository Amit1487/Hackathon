package bhartiairtel.themehackathon.echeck;

import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;

/**
 * Created by B0089798 on 21 / May / 2017 , 1:53 AM.
 * bhartiairtel.themehackathon.echeck
 * Hackathon
 */
public class ECheckResponse {

    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private CheckDetailsBody result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public CheckDetailsBody getResult() {
        return result;
    }

    public void setResult(CheckDetailsBody result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }
}
