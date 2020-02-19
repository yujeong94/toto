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
	
	public Gboard() {}

	public Gboard(int gbNum, String gTitle, String gWriter, int price, String gContent, Date enrollDate, String country,
			String city, Date startDate, Date endDate, String status, int gCount) {
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
	}

	public Gboard(int gbNum, String gTitle, String gWriter, int price, Date enrollDate, String country, String city,
			Date startDate, Date endDate, int gCount) {
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
	
	
}
