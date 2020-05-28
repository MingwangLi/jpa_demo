package com.szjzht.demo.jpa.repository;

import com.szjzht.demo.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:40
 * @Description:
 */
@Repository
public interface UserRepository extends  JpaRepository<User,String>,JpaSpecificationExecutor<User> {
}
