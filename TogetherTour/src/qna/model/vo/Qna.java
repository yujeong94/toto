package qna.model.vo;

import java.sql.Date;

public class Qna {
	private int qNum; 				// 게시글 번호
	private String title;			// 게시글 제목
	private String content;			// 게시글 내용
	private String mId;				// 작성자(memberId)
	private Date createDate;		// 작성일
	private int qCount; 			// 조회수
	private String status;			// 게시글 존재여부
	private String aStatus;			// 게실글 답변여부
	
	public Qna() {}
	
	public Qna(String title, String content, String mId) {
		super();
		this.title = title;
		this.content = content;
		this.mId = mId;
	}

	public Qna(int qNum, String title, String content, String mId, Date createDate, int qCount, String status) {
		super();
		this.qNum = qNum;
		this.title = title;
		this.content = content;
		this.mId = mId;
		this.createDate = createDate;
		this.qCount = qCount;
		this.status = status;
	}
	
	

	public Qna(int qNum, String title, String mId, Date createDate, int qCount, String status) {
		super();
		this.qNum = qNum;
		this.title = title;
		this.mId = mId;
		this.createDate = createDate;
		this.qCount = qCount;
		this.status = status;
	}
	
	

	public Qna(int qNum, String title, String mId, Date createDate, int qCount, String status,
			String aStatus) {
		super();
		this.qNum = qNum;
		this.title = title;
		this.mId = mId;
		this.createDate = createDate;
		this.qCount = qCount;
		this.status = status;
		this.aStatus = aStatus;
	}

	public int getqNum() {
		return qNum;
	}

	public void setqNum(int qNum) {
		this.qNum = qNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getqCount() {
		return qCount;
	}

	public void setqCount(int qCount) {
		this.qCount = qCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getaStatus() {
		return aStatus;
	}

	public void setaStatus(String aStatus) {
		this.aStatus = aStatus;
	}
	

	
	
}
