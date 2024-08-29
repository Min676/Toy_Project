package app.mvc.exception;

public class NoCashException extends Exception {
	public NoCashException(){}
	
	public NoCashException(String Message){
		super(Message);
	}
}
