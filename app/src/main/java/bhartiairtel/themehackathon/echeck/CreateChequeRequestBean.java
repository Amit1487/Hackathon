package bhartiairtel.themehackathon.echeck;

public class CreateChequeRequestBean {

	private String frommobile;
	private String payeemobile;
	private String payeename;
	private String idprooftype;
	private String idprofnumber;
	private Long  amount;
	private String username;
	private String mpin;
	public String getFrommobile() {
		return frommobile;
	}
	public void setFrommobile(String frommobile) {
		this.frommobile = frommobile;
	}
	public String getPayeemobile() {
		return payeemobile;
	}
	public void setPayeemobile(String payeemobile) {
		this.payeemobile = payeemobile;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public String getIdprooftype() {
		return idprooftype;
	}
	public void setIdprooftype(String idprooftype) {
		this.idprooftype = idprooftype;
	}
	public String getIdprofnumber() {
		return idprofnumber;
	}
	public void setIdprofnumber(String idprofnumber) {
		this.idprofnumber = idprofnumber;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMpin() {
		return mpin;
	}
	public void setMpin(String mpin) {
		this.mpin = mpin;
	}
}