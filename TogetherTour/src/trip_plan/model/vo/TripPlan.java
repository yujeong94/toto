package trip_plan.model.vo;

import java.sql.Date;

public class TripPlan {
	private int tPnum; 			// 게시글 번호
	private String title; 		// 게시글 제목
	private String mId; 		// 게시글 작성자
	private int day; 			// 여행일차
	private String content; 	// 여행내용
	private Date createDate; 	// 게시글 작성일
	private String country; 	// 여행국가
	private String city; 		// 여행도시
	private Date startDate; 	// 여행 출발일
	private Date endDate; 		// 여행 도착일
	private String status; 		// 게시글 존재여부
	
	public TripPlan() {}

	public TripPlan(int tPnum, String title, String mId, int day, String content, Date createDate, String country,
			String city, Date startDate, Date endDate, String status) {
		super();
		this.tPnum = tPnum;
		this.title = title;
		this.mId = mId;
		this.day = day;
		this.content = content;
		this.createDate = createDate;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
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

	@Override
	public String toString() {
		return "TripPlan [tPnum=" + tPnum + ", title=" + title + ", mId=" + mId + ", day=" + day + ", content="
				+ content + ", createDate=" + createDate + ", country=" + country + ", city=" + city + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
	
}
