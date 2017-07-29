package com.bpe.monitor.controller;

import com.bpe.monitor.domain.Account;
import com.bpe.monitor.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Signs up a new user with an account.
 *
 * Created by polinchakb on 9/29/16.
 */
@RestController
public class SignUpController {

	private static Logger log = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
    private AccountRepository accountRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Call this api to sign up a new user.  Auth is disabled for this so anyone can call it.
     * @param account The new Account
     * @return The newly created account.
     * @throws Exception If something breaks.
     */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody Account signUp(@RequestBody Account account) throws Exception {
	    account.setPassword(passwordEncoder.encode(account.getPassword()));
	    account.setRole("USER");
		accountRepository.save(account);
		Account result = accountRepository.findByEmail(account.getEmail());
		log.info("Result: "+result);
		return result;
	}

}
