package uni.social.app.exception;

public class DuplicateEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3006558820051723423L;

	public DuplicateEmailException(String message) {
		super(message);
	}
}
