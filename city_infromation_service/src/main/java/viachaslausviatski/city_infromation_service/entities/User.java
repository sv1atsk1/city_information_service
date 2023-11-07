package viachaslausviatski.city_infromation_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import viachaslausviatski.city_infromation_service.entities.enums.Roles;

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

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="image_id")
    private Image avatar;

    @Column(name = "password",length = 100)
    private String password;

    @ElementCollection(targetClass = Roles.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    private LocalDateTime dateOfCreated;

    @OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Event> events = new ArrayList<>();

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();

    }

    public boolean isAdmin() {return roles.contains((Roles.ROLE_ADMIN));}

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
