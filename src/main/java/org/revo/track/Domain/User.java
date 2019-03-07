package org.revo.track.Domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User extends BaseUser {
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password,String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.setType(type);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
