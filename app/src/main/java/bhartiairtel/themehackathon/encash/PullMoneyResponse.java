package bhartiairtel.themehackathon.encash;

import bhartiairtel.themehackathon.pojo.MessageBean;

/**
 * Created by B0089798 on 21 / May / 2017 , 6:41 AM.
 * bhartiairtel.themehackathon.encash
 * Hackathon
 */
public class PullMoneyResponse {


    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private String result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }
}
