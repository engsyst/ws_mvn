package shop.service;

//@WebFault(faultBean="shop.entity.ProductNotAvailableException", messageName="ProductNotAvailableFault", name="ProductNotAvailableException")
public class ProductNotAvailableException extends Exception {
	private static final long serialVersionUID = -1510616808964159070L;

	public ProductNotAvailableException() {
		super();
	}

	public ProductNotAvailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotAvailableException(String message) {
		super(message);
	}

	public ProductNotAvailableException(Throwable cause) {
		super(cause);
	}

}
