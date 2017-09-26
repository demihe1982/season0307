package cn.dyan.services;

import org.springframework.transaction.annotation.Transactional;

public interface AccountService {

    boolean transfer(String accountId,String targetAccount,int amount);

    @Transactional
    boolean insertAccount(String accountId,int amount);

    @Transactional
    boolean createAccountAndTransfer(String newAccount,String targetAccount,int transferAmount);
}
