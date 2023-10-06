package ua.nure.itech.jaxws.service.handled;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ValidationFault", faultBean = "FaultBean")
public class ValidationException extends Exception {

	private static final long serialVersionUID = -1135153123347053147L;
	
	private FaultBean fault;

	public ValidationException() {
		super();
	}
	
	protected ValidationException(FaultBean fault) {
		super(fault.getMessage());
		this.fault = fault;
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
