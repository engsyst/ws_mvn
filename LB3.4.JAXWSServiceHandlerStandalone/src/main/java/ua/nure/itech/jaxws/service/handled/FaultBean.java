package ua.nure.itech.jaxws.service.handled;

public class FaultBean {

	private String code;
	private String message;

	public FaultBean(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public FaultBean() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FaultBean [code=" + code + ", message=" + message + "]";
	}

}
