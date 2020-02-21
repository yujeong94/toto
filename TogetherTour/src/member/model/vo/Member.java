package member.model.vo;

public class Member {
	private String mId;
	private String nickName;
	private String pwd;
	private String userName;
	private String gender;
	private String email;
	private int mKind;
	private int follow;
	private int grade;
	private String age;
	private String status;
	
	public Member() {}

	public Member(String mId, String pwd) {
		super();
		this.mId = mId;
		this.pwd = pwd;
	}
	
	// 아이디 찾기 생성자
	public Member(String mId, String userName, String email, int mKind) {
		super();
		this.mId = mId;
		this.userName = userName;
		this.email = email;
		this.mKind = mKind;
	}
	
	// 비밀번호 찾기 생성자
	public Member(String mId, String pwd, String userName, String email, int mKind) {
		super();
		this.mId = mId;
		this.pwd = pwd;
		this.userName = userName;
		this.email = email;
		this.mKind = mKind;
	}

	public Member(String mId, String nickName, String pwd, String userName, String gender, String email, int mKind,
			int follow, int grade, String age, String status) {
		super();
		this.mId = mId;
		this.nickName = nickName;
		this.pwd = pwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.mKind = mKind;
		this.follow = follow;
		this.grade = grade;
		this.age = age;
		this.status = status;
	}

	public Member(String mId, String nickName, String pwd, String userName, String gender, String email, int mKind, String age) {
		super();
		this.mId = mId;
		this.nickName = nickName;
		this.pwd = pwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.mKind = mKind;
		this.age = age;
	}
	// status만 없는 생성자
	public Member(String mId, String nickName, String pwd, String userName, String gender, String email, int mKind,
			int follow, int grade, String age) {
		super();
		this.mId = mId;
		this.nickName = nickName;
		this.pwd = pwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.mKind = mKind;
		this.follow = follow;
		this.grade = grade;
		this.age = age;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getmKind() {
		return mKind;
	}

	public void setmKind(int mKind) {
		this.mKind = mKind;
	}

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
