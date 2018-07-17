package com.qa.interoperability.soap;

import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.qa.persistence.repo.IAccountRepository;

@WebService(serviceName = "SampleAccountService", portName = "sampleaccount", name = "SampleAccount", endpointInterface = "org.jboss.as.quickstarts.wshelloworld.HelloWorldService",
targetNamespace = "http://www.wildfly.org/quickstarts/wshelloworld/HelloWorld")
public class AccountService implements IAccountService {

	private static final Logger LOGGERSERV = Logger.getLogger(AccountService.class);

	@Inject
	private IAccountRepository repo;

	public String getAllAccounts() {
		LOGGERSERV.info("In AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		LOGGERSERV.info("In AccountServiceImpl addAccount ");
		return repo.createAccount(account);
	}

	@Override
	public String updateAccount(Long id, String account) {
		LOGGERSERV.info("In AccountServiceImpl updateAccount ");
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		LOGGERSERV.info("In AccountServiceImpl deleteAccount ");
		return repo.deleteAccount(id);

	}

	public void setRepo(IAccountRepository repo) {
		this.repo = repo;
	}
}