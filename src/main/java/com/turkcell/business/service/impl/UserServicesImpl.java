package com.turkcell.business.service.impl;

import com.turkcell.bean.PasswordEncoderBean;
import com.turkcell.business.dto.UserRegisterDto;
import com.turkcell.business.service.IUserServices;
import com.turkcell.data.entity.RoleRegisterEntity;
import com.turkcell.data.entity.UserRegisterEntity;
import com.turkcell.data.repository.IUserRegisterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Database
@Transactional

@Service
@Log4j2
public class UserServicesImpl implements IUserServices {

    IUserRegisterRepository repository;
    PasswordEncoderBean passwordEncoderBean;

    //Constructor Injection --> Loose Coupling
    public UserServicesImpl(IUserRegisterRepository repository, PasswordEncoderBean passwordEncoderBean) {
        this.repository = repository;
        this.passwordEncoderBean = passwordEncoderBean;
    }

    //Kullaniciyi database e kaydetmek
    @Override
    public UserRegisterEntity saveUserRegister(UserRegisterDto dto) {
        //RolesRegisterEntity
        List<RoleRegisterEntity> roleRegisterEntityList = new ArrayList<>();
        roleRegisterEntityList.add(new RoleRegisterEntity("ROLES_USER"));

        //UserRegisterEntity
        UserRegisterEntity userRegisterEntity = UserRegisterEntity.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(passwordEncoderBean.passwordEncoderMethod().encode(dto.getPassword()))
                .relationRoleRegisterEntities(roleRegisterEntityList)
                .build();

        return repository.save(userRegisterEntity);
    }

    //UserDetailsService: Sistemdeki kullanici ile etkilesimde bulunmak icin kullaniyoruz
    //public interface IUserServices extends UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // UserRegisterEntity: Once database email bulmamiz gerekiyor
        UserRegisterEntity userRegisterEntity = repository.findByEmail(email);

        //Sistemde bir kullanici yoksa
        if (userRegisterEntity == null) {
            throw new UsernameNotFoundException("Gecersiz Kullanici emaili veya Sifresini yanlis girdiniz");
        }

        //roleAuthories
        //USER ==> Sistemde ekleyecek veriye kullanici emaili veya sifresi buraya ekle
        //Dikkat: Roles ==>  private Collection<RoleRegisterEntity> relationRoleRegisterEntities;
        return new User(userRegisterEntity.getEmail(), userRegisterEntity.getPassword(), roleAuthorities(userRegisterEntity.getRelationRoleRegisterEntities()));
    }

    //Kullanici Role Metodumuz
    private Collection<? extends GrantedAuthority> roleAuthorities(Collection<RoleRegisterEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    //Sistemdeki email ve sifreyi almak
    public UserRegisterEntity findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
