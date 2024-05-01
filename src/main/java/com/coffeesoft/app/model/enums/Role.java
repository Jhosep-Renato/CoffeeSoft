package com.coffeesoft.app.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN(1, "ROLE_ADMIN"),
    CASHIER(2, "ROLE_CASHIER");

    private final int id;
    private final String role;

    Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

}
