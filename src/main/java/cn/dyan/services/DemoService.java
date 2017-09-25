package cn.dyan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Component
public class DemoService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void queryUser(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setName("querytransaction");

        TransactionStatus status = transactionManager.getTransaction(definition);

        Connection cnn = DataSourceUtils.getConnection(dataSource);

        Statement stmt = null;
        try {
            stmt = cnn.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM tb_user");
            while (rs.next()){
//                System.out.println(rs.getString(1)+" , "+rs.getString(2));
            }
            rs.close();
            transactionManager.commit(status);
        } catch (SQLException e) {
            transactionManager.rollback(status);
            DataSourceUtils.releaseConnection(cnn,dataSource);
            e.printStackTrace();
        }

    }

    public boolean insertUser(String uname){
        this.queryUser();
        String sql = " INSERT INTO tb_user(uuid,uname) VALUES('"+ UUID.randomUUID().toString()+"','"+uname+"')";
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setName("inserttransaction");
        TransactionStatus status = transactionManager.getTransaction(definition);
        Connection cnn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        try {
            stmt = cnn.createStatement();
            stmt.execute(sql);
            transactionManager.commit(status);
        } catch (SQLException e) {
            transactionManager.rollback(status);
            DataSourceUtils.releaseConnection(cnn,dataSource);
            e.printStackTrace();
        }
        return true;
    }
}
