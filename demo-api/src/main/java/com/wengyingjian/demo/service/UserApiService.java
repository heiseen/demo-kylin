package com.wengyingjian.demo.service;

import com.wengyingjian.demo.model.User;
import com.wengyingjian.demo.model.query.UserQuery;
import com.wengyingjian.kylin.util.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wengyingjian on 16/1/24.
 */
@Service
public class UserApiService {

    private String queueName = "user";
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public List<User> getUsers() {
        UserQuery userQuery = new UserQuery();
        return userService.getUsers(userQuery).getData();
    }

    public User getUser(Integer userId) {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(userId);
        List<User> userList = userService.getUsers(userQuery).getData();
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

}
