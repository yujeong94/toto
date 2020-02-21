package guide.model.vo;

import java.sql.Date;

public class gReply {
	private int grId;
	private String grContent;
	private int refGid;
	private String writer;
	private Date creatDate;
	private String status;
	
	public gReply () {}

	public gReply(int grId, String grContent, int refGid, String writer, Date creatDate, String status) {
		super();
		this.grId = grId;
		this.grContent = grContent;
		this.refGid = refGid;
		this.writer = writer;
		this.creatDate = creatDate;
		this.status = status;
	}

	public gReply(int grId, String grContent, int refGid, String writer, Date creatDate) {
		super();
		this.grId = grId;
		this.grContent = grContent;
		this.refGid = refGid;
		this.writer = writer;
		this.creatDate = creatDate;
	}

	public int getGrId() {
		return grId;
	}

	public void setGrId(int grId) {
		this.grId = grId;
	}

	public String getGrContent() {
		return grContent;
	}

	public void setGrContent(String grContent) {
		this.grContent = grContent;
	}

	public int getRefGid() {
		return refGid;
	}

	public void setRefGid(int refGid) {
		this.refGid = refGid;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
