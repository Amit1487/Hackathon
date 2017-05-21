package bhartiairtel.themehackathon.dependents;

/**
 * Created by B0089798 on 21 / May / 2017 , 4:50 AM.
 * bhartiairtel.themehackathon.dependents
 * Hackathon
 */

public class AddDependentsRequest {

    String username;//":"8698015062",
    String dependentmobile;//":"9739006136",
    String relation;//":"Son",
    String amount;//":"500"

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDependentmobile() {
        return dependentmobile;
    }

    public void setDependentmobile(String dependentmobile) {
        this.dependentmobile = dependentmobile;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
