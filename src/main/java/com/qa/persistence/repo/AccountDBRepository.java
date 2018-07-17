package com.qa.persistence.repo;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements IAccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Inject
	private static final Logger LOGGERDBREPO = Logger.getLogger(AccountMapRepository.class);
	
	public String getAllAccounts() {
		LOGGERDBREPO.info("In AccountDBRepository method getAllAccounts");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		LOGGERDBREPO.info("In AccountDBRepository method createAccount");
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		LOGGERDBREPO.info("In AccountDBRepository method updateAccount");
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		LOGGERDBREPO.info("In AccountDBRepository method deleteAccount");
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		} else
			LOGGERDBREPO.error("Account does not exist");
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private Account findAccount(Long id) {
		LOGGERDBREPO.info("In AccountDBRepository method findAccount");
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}

