package ua.nure.itech.jaxws.service.handled;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;
import ua.nure.itech.jaxws.service.entity.User;

@WebService(
		endpointInterface = "ua.nure.itech.jaxws.service.handled.Hello", 
		serviceName = "HelloService",
		portName = "HelloPort"
)
@HandlerChain(file = "hello_handler.xml")
// @BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class HelloHandled implements Hello {
	private final Logger log = LoggerFactory.getLogger(HelloHandled.class);

	@Override
	public String hello(User user, 
			SecurityHeader clientToken, 
			Holder<SecurityHeader> serverToken) 
			throws ValidationException {
//		clientToken is processed by SecurityHandler
//		serverToken will be added by SecurityHandler
		if (user == null || user.getName() == null || user.getName().isBlank()) {
			throw new ValidationException("The parameter must be valid: '" + user + "'");
		}
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();){
			BufferedImage avatar = (BufferedImage) user.getAvatar();
			ImageIO.write(avatar, "png", baos);
			byte[] bytes = Base64.getEncoder().encode(baos.toByteArray());
			System.out.println(String.valueOf(Arrays.copyOf(bytes, 20)));
		} catch (IOException e) {
			e.printStackTrace();
			// suppress it
		}
		return "Hello " + user + "!";
	}

}
