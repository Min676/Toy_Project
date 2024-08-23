package app.mvc.model.dto;

public class Membership {
    private int membershipLevel;
    private int pointRate;
    private int levelReq;

    // Getters and Setters
    public int getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(int membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public int getPointRate() {
        return pointRate;
    }

    public void setPointRate(int pointRate) {
        this.pointRate = pointRate;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[membershipLevel=");
		builder.append(membershipLevel);
		builder.append(", pointRate=");
		builder.append(pointRate);
		builder.append(", levelReq=");
		builder.append(levelReq);
		builder.append("]");
		return builder.toString();
	}
    
}
