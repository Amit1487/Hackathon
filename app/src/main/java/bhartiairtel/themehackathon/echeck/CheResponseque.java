package bhartiairtel.themehackathon.echeck;

import bhartiairtel.themehackathon.pojo.MessageBean;

/**
 * Created by B0089798 on 21 / May / 2017 , 3:03 AM.
 * bhartiairtel.themehackathon.echeck
 * Hackathon
 */

public class CheResponseque {


    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private ChequeList result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public ChequeList getResult() {
        return result;
    }

    public void setResult(ChequeList result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }

}
