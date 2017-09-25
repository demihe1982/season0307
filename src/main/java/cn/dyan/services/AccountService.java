package cn.dyan.services;

import cn.dyan.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public boolean insertAccount(String accountId,int amount){
        accountDao.createAccount(accountId,amount);
        return true;
    }

    @Transactional
    public boolean transfer(String accountId,String targetAccount,int amount){

        //当前账号减少
        accountDao.reduceAccount(accountId,amount);
        //目标账户增加
        accountDao.increaseAccount(targetAccount,amount);

        return true;

    }


}
