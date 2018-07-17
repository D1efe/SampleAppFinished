package com.qa.interoperability.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.business.service.AccountService;

@Path("/account")
public class RestEndPoint {

	@Inject
	private AccountService service;

	@Path("/json")
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/json")
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/json/{id}")
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/json/{id}")
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);

	}

	public void setService(AccountService service) {
		this.service = service;
	}

}