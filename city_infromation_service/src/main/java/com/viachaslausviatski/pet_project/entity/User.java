package com.viachaslausviatski.pet_project.entity;

import com.viachaslausviatski.pet_project.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name ="Users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @Column(name = "password",length = 1000)
    private String password;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreated;

    @OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "owner")
    private List<Event> events = new ArrayList<>();

    @OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "owner")
    private List<MyObject> objects = new ArrayList<>();



    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();

    }
    public boolean isAdmin() {return roles.contains((Role.ROLE_ADMIN));}

    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
