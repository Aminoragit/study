package com.example.study.repository;


import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {







//    //User가 있을수도 없을수도 있다 Optional
//    Optional<User>findByAccount(String account);
//
//    Optional<User>findByEmail(String email);
//
//    //where Account=? and  email=?
//    Optional<User>findByAccountAndEmail(String email,String account);

}
