package app.mvc.exception;

/**
 * 중복 발생시 예외
 * */
public class DuplicatedException extends AddException {
	public DuplicatedException() {
	
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
}
