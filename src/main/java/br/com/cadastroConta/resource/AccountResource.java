package br.com.cadastroConta.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.cadastroConta.domain.Account;
import br.com.cadastroConta.dto.AccountDTO;

@Path("/v1/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	@GET
	@Transactional
	public List<Account> getAllAccounts() {
		List<Account> accounts = Account.findAll().list();
		return accounts;
	}
	
	@POST
	@Transactional
	public Account newAccounts(AccountDTO dto) {
		Account account = new Account();
		account.setBank(dto.getBank());
		account.setAgency(dto.getAgency());
		account.setAccount(dto.getAccount());
		account.persistAndFlush();
		return account;
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public Account updateAccount(@PathParam("id") Long id, AccountDTO dto) {
		
		Optional<Account> accountOptional = Account.findByIdOptional(id);
		if(!accountOptional.isPresent()) {
			throw new NotFoundException();
		}
		
		Account account = accountOptional.get();
		account.setBank(dto.getBank());
		account.setAgency(dto.getAgency());
		account.setAccount(dto.getAccount());
		account.persistAndFlush();
		return account;
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void deleteAccount(@PathParam("id") Long id) {
		Optional<Account> accountOptional = Account.findByIdOptional(id);
		if(!accountOptional.isPresent()) {
			throw new NotFoundException();
		}
		
		Account account = accountOptional.get();
		account.delete();
	}
	
}
