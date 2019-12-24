package ua.nure.itech.jaxws.service;

import javax.jws.WebService;

@WebService
public interface HelloMessenger extends Exchanger {
	String hello(String name);
}
