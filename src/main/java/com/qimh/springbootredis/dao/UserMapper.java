package com.qimh.springbootredis.dao;


import com.qimh.springbootredis.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User findUser(String name);

    List<User> findAllUser();

    /**
     *
     * @描述：更新用户信息
     * @创建人：wyait
     * @创建时间：2017年6月29日 下午1:33:09
     * @param user
     * @return
     */
    int update(User user);
}