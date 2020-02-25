package member.model.vo;

import java.sql.Date;

public class mAttachment {
	private int fId;
	private String mId;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int downloadCount;
	private String status;
	
	public mAttachment() {}

	public mAttachment(String mId, String changeName) {
		super();
		this.mId = mId;
		this.changeName = changeName;
	}
	
	public mAttachment(int fId, String mId, String originName, String changeName, String filePath) {
		super();
		this.fId = fId;
		this.mId = mId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}

	public mAttachment(int fId, String mId, String originName, String changeName, String filePath, Date uploadDate,
			int downloadCount, String status) {
		super();
		this.fId = fId;
		this.mId = mId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.downloadCount = downloadCount;
		this.status = status;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
