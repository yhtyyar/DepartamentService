package com.example.DepartmentService.model;

public enum Permission {

    DEPARTMENTS_READ("departments:read"),
    DEPARTMENTS_WRITE("departments:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
