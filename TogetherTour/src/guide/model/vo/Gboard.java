package guide.model.vo;

import java.sql.Date;

public class Gboard {
	private int gbNum;
	private String gTitle;
	private String gWriter;
	private int price;
	private String gContent;
	private Date enrollDate;
	private String country;
	private String city;
	private Date startDate;
	private Date endDate;
	private String status;
	private int gCount;
	private int kind;
	private String kakao;
	
	public Gboard() {}
	
	// 전체 생성자
	public Gboard(int gbNum, String gTitle, String gWriter, int price, String gContent, Date enrollDate, String country,
			String city, Date startDate, Date endDate, String status, int gCount, int kind, String kakao) {
		super();
		this.gbNum = gbNum;
		this.gTitle = gTitle;
		this.gWriter = gWriter;
		this.price = price;
		this.gContent = gContent;
		this.enrollDate = enrollDate;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.gCount = gCount;
		this.kind = kind;
		this.kakao = kakao;
	}
	
	// status제외 생성자 (상세보기)
	public Gboard(int gbNum, String gTitle, String gWriter, int price, String gContent, Date enrollDate, String country,
			String city, Date startDate, Date endDate, int gCount, int kind, String kakao) {
		super();
		this.gbNum = gbNum;
		this.gTitle = gTitle;
		this.gWriter = gWriter;
		this.price = price;
		this.gContent = gContent;
		this.enrollDate = enrollDate;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gCount = gCount;
		this.kind = kind;
		this.kakao = kakao;
	}

	// 리스트 생성자
	public Gboard(int gbNum, String gTitle, String gWriter, int price, Date enrollDate, String country, String city,
			Date startDate, Date endDate, int gCount, int kind) {
		super();
		this.gbNum = gbNum;
		this.gTitle = gTitle;
		this.gWriter = gWriter;
		this.price = price;
		this.enrollDate = enrollDate;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gCount = gCount;
		this.kind = kind;
	}
	
	// 게시판 등록, 수정
	public Gboard(String gTitle, String gWriter, int price, String gContent, String country, String city,
			Date startDate, Date endDate, int kind, String kakao) {
		super();
		this.gTitle = gTitle;
		this.gWriter = gWriter;
		this.price = price;
		this.gContent = gContent;
		this.country = country;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.kind = kind;
		this.kakao = kakao;
	}
	
	public int getGbNum() {
		return gbNum;
	}

	public void setGbNum(int gbNum) {
		this.gbNum = gbNum;
	}

	public String getgTitle() {
		return gTitle;
	}

	public void setgTitle(String gTitle) {
		this.gTitle = gTitle;
	}

	public String getgWriter() {
		return gWriter;
	}

	public void setgWriter(String gWriter) {
		this.gWriter = gWriter;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getgContent() {
		return gContent;
	}

	public void setgContent(String gContent) {
		this.gContent = gContent;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
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

	public int getgCount() {
		return gCount;
	}

	public void setgCount(int gCount) {
		this.gCount = gCount;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}
	
	
}
