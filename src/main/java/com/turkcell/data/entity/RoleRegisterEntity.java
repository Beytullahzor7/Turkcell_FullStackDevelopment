package com.turkcell.data.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Data
@Builder

@Entity
@Table(name = "role_register")
public class RoleRegisterEntity implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private Long rolesId;

    @Column(name = "role_name")
    private String roleName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    //Relation
    //UserRegisterEntity (EAGER)
    //RoleRegisterEntity (LAZY)
    @ManyToMany(mappedBy = "relationRoleRegisterEntities", fetch = FetchType.LAZY)
    private Collection<UserRegisterEntity> userRegisterEntities;

    public RoleRegisterEntity() {
    }

    public RoleRegisterEntity(String roleName) {
        this.roleName = roleName;
    }

    public RoleRegisterEntity(Long rolesId, String roleName, Date createdDate, Collection<UserRegisterEntity> userRegisterEntities) {
        this.rolesId = rolesId;
        this.roleName = roleName;
        this.createdDate = createdDate;
        this.userRegisterEntities = userRegisterEntities;
    }
}

