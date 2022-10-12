package com.turkcell.data.entity;

import javax.persistence.*;

//RememberMe Class
@Entity
@Table(name = "persistent_logins")
public class PersistentRememberMeLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String username;

    @Column(length = 64)
    private String series;

    @Column(length = 64)
    private String token;

    @Column(length = 64)
    private String lastUsed;
}
