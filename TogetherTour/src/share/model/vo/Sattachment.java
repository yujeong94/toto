package share.model.vo ;

import java.sql.Date;

public class Sattachment {
	private	int		fId ;
	private	int		sbNum ;
	private	String	originName ;
	private	String	changeName ;
	private	String	filePath ;
	private	Date	uploadDate ;
	private	int		fileLevel ;
	private	int		downloadCount ;
	private	String	status ;
	 
	public	Sattachment() {}
	public	Sattachment(int sbNum, String changeName) { super(); this.sbNum = sbNum; this.changeName = changeName; }
	public	Sattachment(int fId, int sbNum, String originName, String changeName, String filePath, Date uploadDate, int fileLevel, int downloadCount, String status) { super(); this.fId = fId; this.sbNum = sbNum; this.originName = originName; this.changeName = changeName; this.filePath = filePath; this.uploadDate = uploadDate; this.fileLevel = fileLevel; this.downloadCount = downloadCount; this.status = status; }
	
	public	void	setfId(int fId)						{ this.fId = fId; }
	public	void	setSbNum(int sbNum)					{ this.sbNum = sbNum; }
	public	void	setOriginName(String originName)	{ this.originName = originName; }
	public	void	setChangeName(String changeName)	{ this.changeName = changeName; }
	public	void	setFilePath(String filePath)		{ this.filePath = filePath; }
	public	void	setUploadDate(Date uploadDate)		{ this.uploadDate = uploadDate; }
	public	void	setFileLevel(int fileLevel)			{ this.fileLevel = fileLevel; }
	public	void	setDownloadCount(int downloadCount)	{ this.downloadCount = downloadCount; }
	public	void	setStatus(String status)			{ this.status = status; }
	
	public	int		getfId()			{ return fId; }
	public	int		getSbNum()			{ return sbNum; }
	public	String	getOriginName()		{ return originName; }
	public	String	getChangeName()		{ return changeName; }
	public	String	getFilePath()		{ return filePath; }
	public	Date	getUploadDate()		{ return uploadDate; }
	public	int		getFileLevel()		{ return fileLevel; }
	public	int		getDownloadCount()	{ return downloadCount; }
	public	String	getStatus()			{ return status; }
}
