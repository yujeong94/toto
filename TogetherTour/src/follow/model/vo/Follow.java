package follow.model.vo;

public class Follow {
	private String mId; // 회원 아아디
	private String fId; // 팔로워 아이디
	
	public Follow() {}

	public Follow(String mId, String fId) {
		super();
		this.mId = mId;
		this.fId = fId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}
	
	
	
}
