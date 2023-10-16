package exception;

public class EvalucianException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697825176233506387L;

	public EvalucianException() {
	}

	public EvalucianException(String message) {
		super(message);

	}

	public EvalucianException(Throwable cause) {
		super(cause);

	}

	public EvalucianException(String message, Throwable cause) {
		super(message, cause);

	}

	public EvalucianException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
