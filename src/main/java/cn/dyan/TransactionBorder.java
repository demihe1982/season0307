package cn.dyan;

import cn.dyan.services.AccountService;
import cn.dyan.tx.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionBorder {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);

        AccountService accountService = context.getBean(AccountService.class);
       /* accountService.insertAccount("account1",100);
        accountService.insertAccount("account2",100);*/
        accountService.transfer("account1","account2",100);




    }
}
