package com.szjzht.demo.jpa.service;

import com.szjzht.demo.jpa.entity.User;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:58
 * @Description:
 */
public interface UserService {
    User getUserByUsername(String username);

    void saveUser(User user);

}
