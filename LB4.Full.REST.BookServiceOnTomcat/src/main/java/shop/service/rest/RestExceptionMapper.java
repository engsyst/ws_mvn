package shop.service.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		// лог
		System.out.println("toResponse() exception " + exception.getMessage());
		exception.printStackTrace();
		
		// Вернуть объект класса Response
		return Response.status(getStatusCode(exception)).entity(getEntity(exception)).build();
	}

	/**
	 * Возвращает необходимый HTTP status code для исключения.
	 */
	private int getStatusCode(Throwable exception) {
		if (exception instanceof WebApplicationException) {
			return ((WebApplicationException) exception).getResponse().getStatus();
		}

		return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

	/**
	 * Возвращает тело ответа для исключения.
	 */
	private Object getEntity(Throwable exception) {
		// возвратить трассировку стека (не для production)
		StringWriter errorMsg = new StringWriter();
		exception.printStackTrace(new PrintWriter(errorMsg));
		return errorMsg.toString();
	}
}