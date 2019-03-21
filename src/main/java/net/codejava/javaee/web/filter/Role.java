package net.codejava.javaee.web.filter;

import net.codejava.javaee.entity.User;

public enum Role {

    ADMIN,
    CLIENT;

    public static Role getRole(final User user) {
        return user.getEmail().equals("yosinhasan@gmail.com") ? Role.ADMIN : Role.CLIENT;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public static Role getRole(String role) {
        if (role.equalsIgnoreCase(Role.ADMIN.toString())) {
            return Role.ADMIN;
        } else {
            return Role.CLIENT;
        }
    }

}
