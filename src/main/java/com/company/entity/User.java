package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name ="users")
public class User {

    @Id
    @Column(name = "username",unique=true, columnDefinition="VARCHAR(50)")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Transient
    private String passwordRepeat;

    @Transient
    private String authority;

    public User() {
        this.enabled = 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "{noop}" + password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = "{noop}" +passwordRepeat;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = "ROLE_" + authority;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

}
