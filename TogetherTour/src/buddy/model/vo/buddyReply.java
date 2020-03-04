package buddy.model.vo;

import java.sql.Date;

public class buddyReply {
	private int rId;
	private String rContent;
	private int refBid;
	private String rNick;
	private Date createDate;
	private String status;
	
	public buddyReply() {}

	public buddyReply(int rId, String rContent, int refBid, String rNick, Date createDate,
			String status) {
		this.rId = rId;
		this.rContent = rContent;
		this.refBid = refBid;
		this.rNick = rNick;
		this.createDate = createDate;
		this.status = status;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public int getRefBid() {
		return refBid;
	}

	public void setRefBid(int refBid) {
		this.refBid = refBid;
	}

	public String getrNick() {
		return rNick;
	}

	public void setrNick(String rNick) {
		this.rNick = rNick;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reply [rId=" + rId + ", rContent=" + rContent + ", refBid=" + refBid + ", rNick=" + rNick
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
}
