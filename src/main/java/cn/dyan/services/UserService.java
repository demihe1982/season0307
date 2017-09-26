package cn.dyan.services;

import cn.dyan.exception.CustomizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public boolean createUser(String uname){
        String sql = "INSERT INTO tb_user(uuid,uname) VALUES(?,?)";
        jdbcTemplate.update(sql,new Object[]{UUID.randomUUID().toString(),uname});
        return true;
    }


    @Transactional(rollbackFor = CustomizedException.class)
    public boolean updateUser(String uuid,String uname) throws CustomizedException {
        String sql = "UPDATE tb_user set uname= ? WHERE uuid = ?";
        int nums = jdbcTemplate.update(sql,uname,uuid);

        if(nums == 0 ){
            throw new CustomizedException("未更新任何记录！");
        }
        return nums >0;
    }
}
