package com.turkcell.business.service;

import com.turkcell.business.dto.UserRegisterDto;
import com.turkcell.data.entity.UserRegisterEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

//UserDetailsService: Sistemdeki kullanici ile etkilesimde bulunmak icin kullaniyoruz
public interface IUserServices extends UserDetailsService {

    //Register icin kaydetmek
    UserRegisterEntity saveUserRegister(UserRegisterDto userRegisterDto);
}
