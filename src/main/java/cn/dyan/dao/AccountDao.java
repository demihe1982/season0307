package cn.dyan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createAccount(String accountId,int amount){
        String sql = " INSERT INTO tb_account(account_id,amount) VALUES(?,?)";
       jdbcTemplate.update(sql,accountId,amount);
       return true;
    }


    @Transactional
    public boolean increaseAccount(String accountId,int amount){
        String sql = "UPDATE tb_account SET amount= amount+ ? WHERE account_id=?";
        jdbcTemplate.update(sql,amount,accountId);
        return true;
    }

    @Transactional
    public boolean reduceAccount(String accountId,int amount){
        String sql = "UPDATE tb_account SET amount= amount- ? WHERE account_id=?";
        jdbcTemplate.update(sql,amount,accountId);
        return true;
    }





}
