package buddy_me.model.vo;

public class Report {
	private int rkey;
	private String rmid;
	private String mid;
	private int rsNum;
	private String reason;
	private String status;
	
	public Report() {}

	public Report(String rmid, String mid, int rsNum, String reason) {
		super();
		this.rmid = rmid;
		this.mid = mid;
		this.rsNum = rsNum;
		this.reason = reason;
	}

	public Report(int rkey, String rmid, String mid, int rsNum, String reason) {
		super();
		this.rkey = rkey;
		this.rmid = rmid;
		this.mid = mid;
		this.rsNum = rsNum;
		this.reason = reason;
	}

	public Report(int rkey, String rmid, String mid, int rsNum, String reason, String status) {
		super();
		this.rkey = rkey;
		this.rmid = rmid;
		this.mid = mid;
		this.rsNum = rsNum;
		this.reason = reason;
		this.status = status;
	}

	public int getRkey() {
		return rkey;
	}

	public void setRkey(int rkey) {
		this.rkey = rkey;
	}

	public String getRmid() {
		return rmid;
	}

	public void setRmid(String rmid) {
		this.rmid = rmid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getRsNum() {
		return rsNum;
	}

	public void setRsNum(int rsNum) {
		this.rsNum = rsNum;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
