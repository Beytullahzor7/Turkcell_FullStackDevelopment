package com.turkcell.data.repository;

import com.turkcell.data.entity.UserRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRegisterRepository extends JpaRepository<UserRegisterEntity, Long> {

    //Delivered Query
    //Login sayfasinda kullanici girisi yapmak
    UserRegisterEntity findByEmail(String email);

    //email password sistemde login
    UserRegisterEntity findByEmailAndPassword(String email, String password);
}
