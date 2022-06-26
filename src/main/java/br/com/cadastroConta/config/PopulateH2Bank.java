package br.com.cadastroConta.config;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import br.com.cadastroConta.domain.Account;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class PopulateH2Bank {

	@Transactional
	void startup(@Observes StartupEvent startupEvent) {
		List<Account> accounts = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			accounts.add(createRandomAccount());
		}
		Account.persist(accounts);
		Account.flush();
	}
	
	private Account createRandomAccount() {
		Account account = new Account();
		account.setBank(generateLongRandom());
		account.setAgency(generateLongRandom());
		account.setAccount(generateLongRandom());
		return account;
	}
	
	private Long generateLongRandom() {
		Long leftLimit = 1L;
	    Long rightLimit = 50L;
	    Long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	    return generatedLong;
	}
	
}
