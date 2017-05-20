package bhartiairtel.themehackathon.pojo;

/**
 * Created by B0089737 on 5/20/2017.
 */

public class MessageBean {
    private int statuscode;
    private Object message;

    public int getStatuscode() {
        return statuscode;
    }
    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "MessageBean [ statuscode="+statuscode+",Message="+message+"]";
    }
}

