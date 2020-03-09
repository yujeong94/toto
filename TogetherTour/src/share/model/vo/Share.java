package share.model.vo ; 
import	java.sql.Date ;

public	class Share {
	private	int		sbNum	 ; // 게시글 번호
	private	String	title	 ; // 게시글 제목
	private	String	category ; // 카테고리
	private	String	writer	 ; // 작성자 : MID
	private	Date	date	 ; // 작성일
	private	String	content	 ; // 내용
	private	String	stName	 ; // 거래 상태
	private	String	status	 ; // 게시글 삭제 여부
	private	int		sCount	 ; // 게시글 조회수
	private	String	kakao	 ; // 카카오톡 오픈채팅방 주소
	private	int		kind	 ; // 국내:1 해외:2
	private	String	country  ; // 여행국가
	private	String	city	 ; // 여행도시
	
	public	Share() {}
	public	Share(int sbNum, String title, String category, String writer, Date date, String content, String stName, String status, int sCount, String kakao, int kind, String country, String city) { this.sbNum = sbNum; this.title = title; this.category = category; this.writer = writer; this.date = date; this.content = content; this.stName = stName; this.status = status; this.sCount = sCount; this.kakao = kakao; this.kind = kind; this.country = country; this.city = city; }
		
	public	void	setSbNum(int sbNum)			 { this.sbNum = sbNum; }
	public	void	setTitle(String title)		 { this.title = title; }
	public	void	setCategory(String category) { this.category = category; }
	public	void	setWriter(String writer) 	 { this.writer = writer; }
	public	void	setDate(Date date)			 { this.date = date; }
	public	void	setContent(String content)	 { this.content = content; }
	public	void	setStName(String stName)	 { this.stName = stName; }
	public	void	setStatus(String status)	 { this.status = status; }
	public	void	setSCount(int sCount)		 { this.sCount = sCount; }
	public	void	setKakao(String kakao)		 { this.kakao = kakao; }
	public	void	setKind(int	kind)		 	 { this.kind = kind; }
	public	void	setCountry(String country)	 { this.country = country; }
	public	void	setCity(String city)		 { this.city = city; }
	
	public	int		getSbNum()    { return sbNum; }
	public	String	getTitle()	  { return title; }
	public	String	getCategory() { return category; }
	public	String	getWriter()	  { return writer; }
	public	Date	getDate()	  { return date; }
	public	String	getContent()  { return content; }
	public	String	getStName()	  { return stName; }
	public	String	getStatus()	  { return status; }
	public	int		getsCount()	  { return sCount; }
	public	String	getKakao()	  { return kakao; }
	public	int		getKind()	  { return kind; }
	public	String	getCountry()  { return country; }
	public	String	getCity()	  { return city; }
}
