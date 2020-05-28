package com.szjzht.demo.jpa.service.impl;

import com.szjzht.demo.jpa.entity.User;
import com.szjzht.demo.jpa.repository.UserRepository;
import com.szjzht.demo.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:58
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        Specification<User> userSpecification = (Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.get("username"),username));
            predicate.add(criteriaBuilder.equal(root.get("deleted"),0));
            Predicate[] array = new Predicate[predicate.size()];
            return query.where(predicate.toArray(array)).getRestriction();
        };
        Optional<User> userOptional = userRepository.findOne(userSpecification);
        User user = userOptional.orElse(null);
        return user;
    }
}
