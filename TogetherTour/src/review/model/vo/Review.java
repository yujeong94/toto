package review.model.vo;

import java.sql.Date;

public class Review {
	private int rNum;
	private String title;
	private String mId;
	private String content;
	private int rCount;
	private int rPoint;
	private String location;
	private Date createDate;
	private String Status;
	private Date startDate;
	private Date lastDate;
	private String guide;
	
	private String country;
	private String city;
	private int kind;

	
	
	public Review() {}

	public Review(int rNum, String title, String mId, String content) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
	}

	public Review(int rNum, String title, String mId, String content, Date createDate, String status) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.createDate = createDate;
		this.Status = status;
	}
	
	public Review(int rNum, String title, String mId, int rCount, int rPoint, String location,
			Date createDate, String status) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.location = location;
		this.createDate = createDate;
		this.Status = status;
	}
	
	
	
	

	public Review(int rNum, String title, String mId, String content, int rCount, int rPoint, String location,
			Date createDate, String status) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.location = location;
		this.createDate = createDate;
		this.Status = status;
	}
	
	

	

	
	// list
	public Review(int rNum, String title, String mId, int rCount, int rPoint, Date createDate, String status,
			String country, String city, int kind) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.createDate = createDate;
		this.Status = status;
		this.country = country;
		this.city = city;
		this.kind = kind;
	}

//	public Review(String title, String content, String location,String mId, Date startDate, Date lastDate, String guide) {
//		super();
//		this.title = title;
//		this.content = content;
//		this.location = location;
//		this.mId = mId;
//		this.startDate = startDate;
//		this.lastDate = lastDate;
//		this.guide = guide;
//	}

	public Review(int rNum, String title, String mId, String content, int rCount, int rPoint, String location,
			Date createDate, String status, Date startDate, Date lastDate) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.location = location;
		this.createDate = createDate;
		this.Status = status;
		this.startDate = startDate;
		this.lastDate = lastDate;
	}
	
	

	public Review(int rNum, String title, String mId, String content, int rCount, int rPoint, String location,
			Date createDate, String status, Date startDate, Date lastDate, String guide) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.location = location;
		this.createDate = createDate;
		this.Status = status;
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.guide = guide;
	}
	
	
	
	
	// selectReview
	public Review(int rNum, String title, String mId, String content, int rCount, int rPoint, Date createDate,
			String status, Date startDate, Date lastDate, String guide, String country, String city, int kind) {
		super();
		this.rNum = rNum;
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.rCount = rCount;
		this.rPoint = rPoint;
		this.createDate = createDate;
		this.Status = status;
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.guide = guide;
		this.country = country;
		this.city = city;
		this.kind = kind;
	}

	// 게시판 등록
	public Review(String title, String mId, String content, Date startDate, Date lastDate, String guide, String country,
			String city, int kind) {
		super();
		this.title = title;
		this.mId = mId;
		this.content = content;
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.guide = guide;
		this.country = country;
		this.city = city;
		this.kind = kind;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public int getrCount() {
		return rCount;
	}

	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

	public int getrPoint() {
		return rPoint;
	}

	public void setrPoint(int rPoint) {
		this.rPoint = rPoint;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}
	
	
	
	
	
	
}
