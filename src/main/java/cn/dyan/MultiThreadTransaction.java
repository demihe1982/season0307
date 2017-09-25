package cn.dyan;

import cn.dyan.services.MultiThreadService;
import cn.dyan.services.UserService;
import cn.dyan.tx.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadTransaction {

    public static  void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);

        UserService userService = context.getBean(UserService.class);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
       for(int i = 0 ; i < 2; i++) {
           final int t = i;
           executorService.execute(() -> {
               userService.createUser("abc123_"+t);
           });
       }
    }
}
