package ua.nure.itech.jaxws.service;

import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class Util {

	private Util() { /* hide it */ }

	public static SecurityHeader createSecurityHeader(String token) {
		SecurityHeader header = new SecurityHeader();
		header.setToken(token);
		return header;
	}

}
