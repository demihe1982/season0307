package cn.dyan.tx;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.transaction.config.TransactionManagementConfigUtils;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"cn.dyan.services","cn.dyan.dao"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(){
       return  new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mytx");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    /*@Bean(TransactionManagementConfigUtils.TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME)
    public AnnotationTransactionAspect annotationTransactionAspect(){
        AnnotationTransactionAspect annotationTransactionAspect = AnnotationTransactionAspect.aspectOf();
        annotationTransactionAspect.setTransactionManager(transactionManager());
        return annotationTransactionAspect;
    }*/
}
