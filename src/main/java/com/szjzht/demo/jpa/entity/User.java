package com.szjzht.demo.jpa.entity;

import javax.persistence.Entity;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:31
 * @Description:
 */
@Entity
public class User extends Base{

    private String username;

    private String password;

    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
