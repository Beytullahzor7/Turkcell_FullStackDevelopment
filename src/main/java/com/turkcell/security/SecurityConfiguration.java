package com.turkcell.security;

import com.turkcell.bean.PasswordEncoderBean;
import com.turkcell.bean.PersistTokenRepositoryBean;
import com.turkcell.business.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Sistemdeki Kullaniciyi Gostermek
    @Autowired
    IUserServices iUserServices;

    //Login Encoder-Decoder
    //passwordEncoderBean: Sifrelerimize maskeleme yapmak icin kullaniriz
    @Autowired
    private PasswordEncoderBean passwordEncoderBean;

    //Remember Me-1
    @Autowired
    private UserDetailsService userDetailsService;

    //Remember Me-2
    @Autowired
    private PersistTokenRepositoryBean persistTokenRepositoryBean;

    //Sistemde izin verilecek olan endpointleri belirlemek
    //login,logout,register,root,index izin vermek
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login")
                //Eger login olursam beni hangi sayfaya redirect edecek
                .defaultSuccessUrl("/admin", true).permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                //Saniye Cinsinden 1 yil = 31.104.000 saniye
                .tokenValiditySeconds(1 * 60 * 60 * 24 * 30 * 12) //1 Yil
                .tokenRepository(persistTokenRepositoryBean.persistentTokenRepositoryBeanMethod())
                .userDetailsService(userDetailsService);
    }

    //PasswordEncoder, kullanici kendisine atananan UserDetailsService ile yuklesin ve dogrulasin
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderMyBean() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(iUserServices);
        //Maskeleme Islemleri
        authenticationProvider.setPasswordEncoder(passwordEncoderBean.passwordEncoderMethod());
        return authenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProviderMyBean());
    }
}
