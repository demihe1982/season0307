package cn.dyan;

import cn.dyan.exception.CustomizedException;
import cn.dyan.services.UserService;
import cn.dyan.tx.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomizedTransaction {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
        UserService userService = context.getBean(UserService.class);
        try {
            userService.updateUser("1241242","testException");
        } catch (CustomizedException e) {
            e.printStackTrace();
        }
    }
}
