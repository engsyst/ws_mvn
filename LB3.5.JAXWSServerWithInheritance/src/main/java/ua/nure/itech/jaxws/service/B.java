package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class B extends A {
	
//	@Override
//	public String m(@WebParam(name="p")  String p) {
//		return "Override" + super.m(p);
//	}
//
	@WebMethod
	public int b(@WebParam(name="p") int p) {
		return p;
	}
}
