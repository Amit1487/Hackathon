package bhartiairtel.themehackathon.encash;

import bhartiairtel.themehackathon.pojo.MessageBean;

/**
 * Created by B0089798 on 21 / May / 2017 , 2:26 AM.
 * bhartiairtel.themehackathon.encash
 * Hackathon
 */

public class EncashResponse {


    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private EnCashResult result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public EnCashResult getResult() {
        return result;
    }

    public void setResult(EnCashResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }

}
