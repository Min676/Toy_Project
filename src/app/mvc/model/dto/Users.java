package app.mvc.model.dto;

public class Users {
	private int userSeq;
	private String userId;
	private String name;
	private String pw;
	private int point;
	private int membershipLevel;
	private int ocount;
	private int walletseq;
	private int cash;
	
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
	
    public Users(int userSeq, String userId, String name, String pw, int point, int membershipLevel, int ocount, int walletseq, int cash) {
        this.userSeq = userSeq;
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.point = point;
        this.membershipLevel = membershipLevel;
        this.ocount = ocount;
        this.walletseq = walletseq;
        this.cash = cash;
    }
	

	public Users(String userId, String name, String pw) {
		super();
		this.userId = userId;
		this.name = name;
		this.pw = pw;
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

	public int getWalletseq() {
		return walletseq;
	}
	public void setWalletseq(int walletseq) {
		this.walletseq = walletseq;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("회원 번호 = ");
		builder.append(userSeq);
		builder.append("\n 회원 ID = ");
		builder.append(userId);
		builder.append("\n 회원 이름 = ");
		builder.append(name);
		builder.append("\n 회원 비밀번호= ");
		builder.append(pw);
		builder.append("\n 포인트 잔액 = ");
		builder.append(point);
		builder.append("\n 멤버쉽 레벨 = ");
		builder.append(membershipLevel);
		builder.append("\n 멤버쉽 카운트= ");
		builder.append(ocount);
		builder.append("\n 지갑 번호 = ");
		builder.append(walletseq);
		builder.append("\n 지갑 잔액 = ");
		builder.append(cash);
		
		return builder.toString();
	}

}
