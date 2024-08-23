package app.mvc.model.dto;

public class Users {
	private int userSeq;
	private String userId;
	private String name;
	private String pw;
	private int point;
	private int membershipLevel;
	private int ocount;
	
	public Users() {}
	public Users(int userSeq, String userId, String name, String pw, int point, int membershipLevel,
			int ocount) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.point = point;
		this.membershipLevel = membershipLevel;
		this.ocount = ocount;
	}

	// Getters and Setters
	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMembershipLevel() {
		return membershipLevel;
	}

	public void setMembershipLevel(int membershipLevel) {
		this.membershipLevel = membershipLevel;
	}

	public int getOcount() {
		return ocount;
	}

	public void setOcount(int ocount) {
		this.ocount = ocount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[userSeq=");
		builder.append(userSeq);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pw=");
		builder.append(pw);
		builder.append(", point=");
		builder.append(point);
		builder.append(", membershipLevel=");
		builder.append(membershipLevel);
		builder.append(", ocount=");
		builder.append(ocount);
		builder.append("]");
		return builder.toString();
	}

}
