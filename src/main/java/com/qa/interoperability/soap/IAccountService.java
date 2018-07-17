package com.qa.interoperability.soap;

import javax.jws.*;

@WebService(targetNamespace = "http://localhost:8080")
public interface IAccountService {

	@WebMethod
	String getAllAccounts();

	@WebMethod
	String addAccount(String account);

	@WebMethod
	String updateAccount(Long id, String account);

	@WebMethod
	String deleteAccount(Long id);

}
