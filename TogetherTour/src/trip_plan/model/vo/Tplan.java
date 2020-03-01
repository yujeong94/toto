package trip_plan.model.vo;

import java.sql.Date;

public class Tplan {
	private int tPnum; 			// 게시글번호
	private String title; 		// 게시글제목
	private String mId; 		// 게시글작성자
	private int day; 			// 여행일차
	private String content; 	// 여행내용
	private Date createDate; 	// 게시글작성일
	private String country; 	// 여행국가
	private String city; 		// 여행도시
	private int tCount;			// 조회수
	private Date startDate; 	// 여행출발일
	private Date endDate; 		// 여행도착일
	private String status; 		// 게시글 존재여부
	private int kind;			// 국내/해외 구분(국내1, 해외2)
	
	public Tplan() {}
	
	
	public Tplan(String title, String mId, int day, String content, String country, String city, 
			Date startDate, Date endDate, String status, int kind) {
		super();
		this.title = title;
		this.mId = mId;
		this.day = day;
		this.content = content;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.kind = kind;
	}



	public Tplan(int tPnum, String title, String mId, int day, String content, Date createDate, String country,
			String city, int tCount, Date startDate, Date endDate, String status, int kind) {
		super();
		this.tPnum = tPnum;
		this.title = title;
		this.mId = mId;
		this.day = day;
		this.content = content;
		this.createDate = createDate;
		this.country = country;
		this.city = city;
		this.tCount = tCount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.kind = kind;
	}



	public int gettPnum() {
		return tPnum;
	}



	public void settPnum(int tPnum) {
		this.tPnum = tPnum;
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



	public int getDay() {
		return day;
	}



	public void setDay(int day) {
		this.day = day;
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



	public int gettCount() {
		return tCount;
	}



	public void settCount(int tCount) {
		this.tCount = tCount;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getKind() {
		return kind;
	}



	public void setKind(int kind) {
		this.kind = kind;
	}

}
