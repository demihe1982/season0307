package cn.dyan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MultiThreadService {

    @Autowired
    private UserService userService;

    @Transactional
    public void executeBatch(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0 ; i < 2; i++) {
            final int t = i;
            executorService.execute(() -> {
                userService.createUser("abc123_"+t);
            });
        }
    }

}
