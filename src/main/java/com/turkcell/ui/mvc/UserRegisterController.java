package com.turkcell.ui.mvc;

import com.turkcell.business.dto.UserRegisterDto;
import com.turkcell.business.service.IUserServices;
import com.turkcell.data.entity.UserRegisterEntity;
import com.turkcell.data.repository.IUserRegisterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Log4j2
public class UserRegisterController {

    IUserServices services;
    IUserRegisterRepository repository;

    public UserRegisterController(IUserServices services, IUserRegisterRepository repository) {
        this.services = services;
        this.repository = repository;
    }

    // HomePage
    // http://localhost:8080/
    // http://localhost:8080/index
    @GetMapping({"/", "/index"})
    public String homePage(@PathVariable(name = "index", required = false) String path) {
        return "index";
    }

    //Login
    // http:localhost:8080/login
    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Adi: " + authentication.getPrincipal());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());

        if (authentication.getPrincipal() == "anonymousUser") {
            System.out.println("Kullanici Yok");
            return "/login";
        } else {
            System.out.println("Kullanici Var");
        }
        return "redirect:/admin?success";
    }

    @GetMapping("/admin")
    public String adminPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());
        return "/admin/index";
    }

    @GetMapping("/login-user")
    public String loginUser(UserRegisterEntity userRegisterEntity, HttpServletRequest request, HttpServletResponse response) {
        //email ve sifre varsa
        if (repository.findByEmailAndPassword(userRegisterEntity.getEmail(), userRegisterEntity.getPassword()) != null) {
        }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            return "login";
//        }
//
//        HttpSession session = request.getSession();
//        if (session != null) {
//            return "login_page";
//        }

        return "index";
    }


    // ###################################
    // register ==> GetMapping
    // http://localhost:8080/user/register
    @GetMapping("/user/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    // register ==> PostMapping
    // http://localhost:8080/user/register
    @PostMapping("/user/register")
    public String postRegisterForm(@Valid @ModelAttribute("user") UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        log.info("Log4j2 " + userRegisterDto);
        if (bindingResult.hasErrors()) {
            log.error("Kaydetme Hatasi " + userRegisterDto);
            return "register";
        }

        services.saveUserRegister(userRegisterDto);
        return "redirect:/login?success";
    }
}
