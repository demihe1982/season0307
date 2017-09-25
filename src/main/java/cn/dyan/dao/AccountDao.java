package cn.dyan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createAccount(String accountId,int amount){
        String sql = " INSERT INTO tb_account(account_id,amount) VALUES(?,?)";
       jdbcTemplate.update(sql,accountId,amount);
       return true;
    }

    /**
     * 账户金额增加
     * @param accountId
     * @param amount
     * @return
     */
    public boolean increaseAccount(String accountId,int amount){
        String sql = "UPDATE tb_account SET amount= amount+ ? WHERE account_id=?";
        jdbcTemplate.update(sql,amount,accountId);
        return true;
    }

    /**
     * 账号金额减少
     * @param accountId
     * @param amount
     * @return
     */
    public boolean reduceAccount(String accountId,int amount){
        String sql = "UPDATE tb_account SET amount= amount- ? WHERE account_id=?";
        jdbcTemplate.update(sql,amount,accountId);
        return true;
    }





}
