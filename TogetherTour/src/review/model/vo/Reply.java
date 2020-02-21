package review.model.vo;

import java.sql.Date;

public class Reply {
	private int rId;
	private String rContent;
	private int refrNum; // 댓글이 어느 게시판에 속해있는지 확인하는번호
	private String rWriter;
	private Date createDate;
	private String status;
	
	
	public Reply() {}


	public Reply(int rId, String rContent, int refrNum, String rWriter, Date createDate,String status) {
		super();
		this.rId = rId;
		this.rContent = rContent;
		this.refrNum = refrNum;
		this.rWriter = rWriter;
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


	public int getRefrNum() {
		return refrNum;
	}


	public void setRefrNum(int refrNum) {
		this.refrNum = refrNum;
	}


	public String getrWriter() {
		return rWriter;
	}


	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
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
		return "Reply [rId=" + rId + ", rContent=" + rContent + ", refrNum=" + refrNum + ", rWriter=" + rWriter
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}


	
	
	
}
