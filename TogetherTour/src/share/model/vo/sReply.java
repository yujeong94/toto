package share.model.vo;

import java.sql.Date;

public	class sReply {
	private int rId;
	private String content;
	private int sbNum;
	private String writer;
	private Date createDate;
	private String status;
	
	public	sReply () {}
	public	sReply(int rId, String content, int sbNum, String writer, Date createDate) { this.rId = rId; this.content = content; this.sbNum = sbNum; this.writer = writer; this.createDate = createDate; }
	public	sReply(int rId, String content, int sbNum, String writer, Date createDate, String status) { this.rId = rId; this.content = content; this.sbNum = sbNum; this.writer = writer; this.createDate = createDate; this.status = status; }
	
	public	int		getrId()		{ return rId; }
	public	String	getContent()	{ return content; }
	public	int		getSbNum()		{ return sbNum; }
	public	String	getWriter()		{ return writer; }
	public	Date	getCreateDate()	{ return createDate; }
	public	String	getStatus()		{ return status; }
	
	public	void	setrId(int rId)					{ this.rId = rId; }
	public	void	setContent(String content)		{ this.content = content; }
	public	void	setSbNum(int sbNum)				{ this.sbNum = sbNum; }
	public	void	setWriter(String writer)		{ this.writer = writer; }
	public	void	setCreateDate(Date createDate)	{ this.createDate = createDate; }
	public	void	setStatus(String status)		{ this.status = status; }
}
