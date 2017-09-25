package cn.dyan;

import cn.dyan.services.AccountService;
import cn.dyan.tx.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectjTransaction {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
        AccountService service = context.getBean(AccountService.class);

        service.createAccountAndTransfer("account001","account1",200);


    }
}
