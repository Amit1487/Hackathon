package bhartiairtel.themehackathon.pojo;

/**
 * Created by B0089737 on 5/20/2017.
 */

public class LoginResponse {

    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    private Object result;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO [messageBean=" + messageBean + ", result=" + result + "]";
    }


}
