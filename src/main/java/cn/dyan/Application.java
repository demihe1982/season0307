package cn.dyan;

import cn.dyan.services.DemoService;
import cn.dyan.tx.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
//        DataSource dataSource = context.getBean(DataSource.class);
//        Connection connection = DataSourceUtils.getConnection(dataSource);
//        Statement stmt = null;
//        try {
//            stmt = connection.createStatement();
//            ResultSet rs =  stmt.executeQuery("SELECT * FROM tb_user");
//            while (rs.next()){
//                System.out.println(rs.getString(0)+" , "+rs.getString(1));
//            }
//            rs.close();
//        } catch (SQLException e) {
//            DataSourceUtils.releaseConnection(connection,dataSource);
//        }

        DemoService demoService = context.getBean(DemoService.class);
//        demoService.queryAndInsert();
        demoService.insertUser("txtest001");
    }
}
