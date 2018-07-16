package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.repo.IAccountRepository;

public class AccountService implements IAccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private IAccountRepository repo;

	public String getAllAccounts() {
		LOGGER.info("In AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		return repo.createAccount(account);
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);

	}

	public void setRepo(IAccountRepository repo) {
		this.repo = repo;
	}
}