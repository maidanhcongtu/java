package com.mhdanh.mytemplate.dao;

import com.mhdanh.mytemplate.domain.Account;

public interface AccountDao extends CommonDao<Account>{
	boolean existUsernameAndPasword(String username,String password);
	Account getAccountByUsername(String username);
	Account getAccountByEmail(String email);
	Account getAccountByToken(String token);
	Account getAccountByKeyRecoverPassword(String tokenRecoverPassword);
}
