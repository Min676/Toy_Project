package app.mvc.model.dto;

public class Wallet {
    private int walletSeq;
    private int userSeq;
    private int cash;

    // 기본 생성자
    public Wallet() {
    }

    // 매개변수가 있는 생성자
    public Wallet(int walletSeq, int userSeq, int cash) {
        this.walletSeq = walletSeq;
        this.userSeq = userSeq;
        this.cash = cash;
    }

    // Getters and Setters
    public int getWalletSeq() {
        return walletSeq;
    }

    public void setWalletSeq(int walletSeq) {
        this.walletSeq = walletSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
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
		builder.append("[walletSeq=");
		builder.append(walletSeq);
		builder.append(", userSeq=");
		builder.append(userSeq);
		builder.append(", cash=");
		builder.append(cash);
		builder.append("]");
		return builder.toString();
	}
   
 
}
