package com.qa.interoperability.rest;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;

import com.qa.business.service.AccountService;

@ApplicationPath("soap")
public class SOAPActivator extends Application {

	@Inject
	private AccountService service;

	@Path("/xml")
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/xml")
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/xml/{id}")
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/xml/{id}")
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);

	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
