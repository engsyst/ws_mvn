package shop.service;

//@WebFault(faultBean="shop.entity.ProductNotValidException", messageName="ProductNotValidFault", name="ProductNotValidException")
public class ProductNotValidException extends Exception {

	private static final long serialVersionUID = -3756662098371106639L;

	public ProductNotValidException() {
		super();
	}

	public ProductNotValidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotValidException(String message) {
		super(message);
	}

	public ProductNotValidException(Throwable cause) {
		super(cause);
	}

}
