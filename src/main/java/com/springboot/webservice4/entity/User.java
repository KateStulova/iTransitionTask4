package com.springboot.webservice4.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, message = "At least 1 symbol")
    private String username;
    @Size(min = 1, message = "At least 1 symbol")
    private String email;
    @CreationTimestamp
    private Timestamp signUp;
    private Timestamp signIn;
    @Size(min = 1, message = "At least 1 symbol")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Status> statuses;

    boolean isBlocked;

    public User() {
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    public Timestamp getSignUp() {
        return signUp;
    }

    public Timestamp getSignIn() {
        return signIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSignUp(Timestamp signUp) {
        this.signUp = signUp;
    }

    public void setSignIn(Timestamp signIn) {
        this.signIn = signIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getStatuses();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<Status> roles) {
        this.statuses = statuses;
    }
}
