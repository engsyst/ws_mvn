package ua.nure.itech.jaxws.service.handled;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ValidationException", faultBean = "ValidationExceptionInfo")
public class ValidationException extends Exception {

	private static final long serialVersionUID = -1135153123347053147L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

}
