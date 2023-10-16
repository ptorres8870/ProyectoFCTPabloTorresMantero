package exception;

public class AutenticarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992934743990817429L;

	public AutenticarException() {
	}

	public AutenticarException(String message) {
		super(message);

	}

	public AutenticarException(Throwable cause) {
		super(cause);

	}

	public AutenticarException(String message, Throwable cause) {
		super(message, cause);

	}

	public AutenticarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
