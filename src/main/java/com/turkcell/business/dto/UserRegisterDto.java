package com.turkcell.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {

    //Validation islemleri yapilacak
    private Long id;
    @NotEmpty(message = "Kullanici Adi Bos Gecilemez!")
    private String name;
    @NotEmpty(message = "Kullanici Soyadi Bos Gecilemez!")
    private String surname;
    @NotEmpty(message = "Kullanici Emaili Bos Gecilemez!")
    @Email(message = "Uygun Formatta Bir Email Giriniz example: deneme@gmail.com")
    private String email;
    @NotEmpty(message = "Sifre Bos Gecilemez")
    @Size(min = 7, max = 25, message = "Kullanici sifresi min 7 max 25 karakter icermelidir")
    @Pattern(regexp = "^[a-zA-Z0-9]{7}")
    private String password;
    private String picture = "resim.png";
    private boolean isActive = false;

    //Parametreli Constructor
    public UserRegisterDto(String name, String surname, String email, String password, String picture, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.isActive = isActive;
    }
}
