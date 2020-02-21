package buddy.model.vo;

import java.sql.Date;

public class buddyBoard {

		
	private int bnum;			// 게시글 번호
	private String title;		//제목
	private String part_yn;		// 동행참여구분
	private String content;		// 내용
	private Date create_date;	// 게시글 작성일		
	private String nick;		// 작성자_MID와...
	private String country;		// 국가
	private String city;		// 도시
	private Date start_date;	// 여행시작	
	private Date end_date; 		// 여행종료
	private String status; 		// 해외 국내	
	private int bcount; 		// 게시글 조회수
	private String theme; 		// 여행테마
	private int group_age; 		// 연령대
	private String kakao; 		// 계정연결
	private int kind; 			// 국내해외 구분
	private int head_cnt;       // 동행인원
	
	// 기본생성자
	public buddyBoard() {}

	// 전체
	public buddyBoard(int bnum, String title, String part_yn, String content, Date create_date, String nick,
			String country, String city, Date start_date, Date end_date, String status, int bcount, String theme,
			int group_age, String kakao, int kind, int head_cnt) {
				this.bnum = bnum;
				this.title = title;
				this.part_yn = part_yn;
				this.content = content;
				this.create_date = create_date;
				this.nick = nick;
				this.country = country;
				this.city = city;
				this.start_date = start_date;
				this.end_date = end_date;
				this.status = status;
				this.bcount = bcount;
				this.theme = theme;
				this.group_age = group_age;
				this.kakao = kakao;
				this.kind = kind;
				this.head_cnt = head_cnt;
	}

	
	public buddyBoard(int bnum, String title, String content, Date create_date, String nick, String country,
			String city, Date start_date, int bcount, String theme, int group_age, String kakao, int kind) {
		this.bnum = bnum;
		this.title = title;
		this.content = content;
		this.create_date = create_date;
		this.nick = nick;
		this.country = country;
		this.city = city;
		this.start_date = start_date;
		this.bcount = bcount;
		this.theme = theme;
		this.group_age = group_age;
		this.kakao = kakao;
		this.kind = kind; 
	}
	
	// 동행게시판 리스트
	public buddyBoard(int bnum, String title, Date create_date, String nick, String country, String city,
			Date start_date, int bcount, int head_cnt, String content) {
		
		this.bnum = bnum;
		this.title = title;
		this.create_date = create_date;
		this.nick = nick;
		this.country = country;
		this.city = city;
		this.start_date = start_date;
		this.bcount = bcount;
		this.head_cnt = head_cnt;
		this.content = content;
	}
	
	//등록폼
	public buddyBoard(int bnum, String title, Date create_date, String nick, String country, String city,
			Date start_date, int bcount, int head_cnt, String content, String theme, int group_age, String kakao) {
		
		this.bnum = bnum;
		this.title = title;
		this.create_date = create_date;
		this.nick = nick;
		this.country = country;
		this.city = city;
		this.start_date = start_date;
		this.bcount = bcount;
		this.head_cnt = head_cnt;
		this.content = content;
		this.theme=theme;
		this.group_age =group_age;
		this.kakao = kakao;
	}
	
	
	
	public int getBnum() {
		return bnum;
	}



	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPart_yn() {
		return part_yn;
	}

	public void setPart_yn(String part_yn) {
		this.part_yn = part_yn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getnick() {
		return nick;
	}

	public void setnick(String nick) {
		this.nick = nick;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getGroup_age() {
		return group_age;
	}

	public void setGroup_age(int group_age) {
		this.group_age = group_age;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getHead_cnt() {
		return head_cnt;
	}

	public void setHead_cnt(int head_cnt) {
		this.head_cnt = head_cnt;
	}

	


	
}
