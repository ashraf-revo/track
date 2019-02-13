package org.revo.track.Domain;

import lombok.Getter;

import static org.revo.track.Domain.Role.Paths.ADMIN_PATH;
import static org.revo.track.Domain.Role.Paths.USER_PATH;

@Getter
public enum Role {
    USER("USER", USER_PATH), ADMIN("ADMIN", ADMIN_PATH);
    private String role;
    private String path;

    Role(String role, String path) {
        this.role = role;
        this.path = path;
    }

    public String getBuildRole() {
        return "ROLE_" + role;
    }

    public static class Paths {
        public static final String USER_PATH = "/api/user";
        public static final String ADMIN_PATH = "/api/admin";
    }
}