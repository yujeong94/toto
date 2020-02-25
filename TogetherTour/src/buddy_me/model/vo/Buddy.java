package buddy_me.model.vo;

public class Buddy {
	private int bNum;
	private String mId;
	private int headCnt;
	private String partYN;
	
	public Buddy () {}

	public Buddy(String mId, int headCnt) {
		super();
		this.mId = mId;
		this.headCnt = headCnt;
	}

	public Buddy(int bNum, String mId, int headCnt, String partYN) {
		super();
		this.bNum = bNum;
		this.mId = mId;
		this.headCnt = headCnt;
		this.partYN = partYN;
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

	public int getHeadCnt() {
		return headCnt;
	}

	public void setHeadCnt(int headCnt) {
		this.headCnt = headCnt;
	}

	public String getPartYN() {
		return partYN;
	}

	public void setPartYN(String partYN) {
		this.partYN = partYN;
	}
}
