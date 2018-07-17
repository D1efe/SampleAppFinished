package com.qa.persistence.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Transaction;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements IAccountRepository {

	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Account> accountMap;
	private Long ID;

	@Inject
	private JSONUtil util;
	
	@Inject
	private static final Logger LOGGERMAPREPO = Logger.getLogger(AccountMapRepository.class);

	public AccountMapRepository() {
		LOGGERMAPREPO.info("in AccountMapRepository method constrctor");
		this.accountMap = new HashMap<Long, Account>();
		ID = INITIAL_COUNT;
		initAccountMap();
	}

	public String getAllAccounts() {
		LOGGERMAPREPO.info("in AccountMapRepository method getAllAccounts");
		return util.getJSONForObject(accountMap.size());
	}

	public String createAccount(String account) {
		LOGGERMAPREPO.info("in AccountMapRepository method createAccount");
		ID++;
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(ID, newAccount);
		return account;
	}

	public String updateAccount(Long id, String accountToUpdate) {
		LOGGERMAPREPO.info("in AccountMapRepository method updateAccount");
		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id, newAccount);
		return accountToUpdate;
	}

	public String deleteAccount(Long id) {
		LOGGERMAPREPO.info("in AccountMapRepository method deleteAccount");
		accountMap.remove(id);
		return "{\"message\": \"accout sucessfully removed\"}";
	}

	private void initAccountMap() {
		LOGGERMAPREPO.info("in AccountMapRepository method initAccountMap");
		Transaction transaction = new Transaction("sample");
		transaction.setId(1L);
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Account account = new Account("Joe", "Bloggs", "1234",transactions);
		accountMap.put(1L, account);
	}

}

