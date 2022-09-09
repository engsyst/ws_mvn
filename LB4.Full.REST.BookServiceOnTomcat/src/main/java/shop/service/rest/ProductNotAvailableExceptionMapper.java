package shop.service.rest;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import shop.service.ProductNotAvailableException;

@Provider
public class ProductNotAvailableExceptionMapper implements ExceptionMapper<ProductNotAvailableException> {

	@Override
	public Response toResponse(ProductNotAvailableException exception) {
		// лог
		System.out.println("toResponse() exception " + exception.getMessage());
		exception.printStackTrace();

		// Вернуть объект класса Response
		return Response.status(Response.Status.NOT_FOUND.getStatusCode())
				.entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN_TYPE)
				.build();
	}

}