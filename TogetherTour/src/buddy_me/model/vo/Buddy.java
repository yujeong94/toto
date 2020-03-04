package buddy_me.model.vo;

import java.sql.Date;

public class Buddy {
	private int bNum;
	private String mId;
	private String nick;
	private String name;
	private String writer_mid;
	private String writer_nick;
	private String writer_name;
	private String country;
	private String city;
	private Date start_date;
	private Date end_date;
	private int headCnt;
	
	public Buddy () {}

	public Buddy(String mId, String nick, String name, String writer_nick, String writer_name, String country, String city,
			Date start_date, Date end_date, int headCnt) {
		super();
		this.mId = mId;
		this.nick = nick;
		this.name = name;
		this.writer_nick = writer_nick;
		this.writer_name = writer_name;
		this.country = country;
		this.city = city;
		this.start_date = start_date;
		this.end_date = end_date;
		this.headCnt = headCnt;
	}

	public Buddy(int bNum, String mId, String nick, String name, String writer_mid, String writer_nick,
			String writer_name, String country, String city, Date start_date, Date end_date, int headCnt) {
		super();
		this.bNum = bNum;
		this.mId = mId;
		this.nick = nick;
		this.name = name;
		this.writer_mid = writer_mid;
		this.writer_nick = writer_nick;
		this.writer_name = writer_name;
		this.country = country;
		this.city = city;
		this.start_date = start_date;
		this.end_date = end_date;
		this.headCnt = headCnt;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter_mid() {
		return writer_mid;
	}

	public void setWriter_mid(String writer_mid) {
		this.writer_mid = writer_mid;
	}

	public String getWriter_nick() {
		return writer_nick;
	}

	public void setWriter_nick(String writer_nick) {
		this.writer_nick = writer_nick;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getHeadCnt() {
		return headCnt;
	}

	public void setHeadCnt(int headCnt) {
		this.headCnt = headCnt;
	}

}
