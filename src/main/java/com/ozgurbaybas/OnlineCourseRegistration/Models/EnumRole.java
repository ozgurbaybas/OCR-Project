package com.ozgurbaybas.OnlineCourseRegistration.Models;

public enum EnumRole {
    ROLE_USER("user"),
    ROLE_ADMIN("admin"),
    ROLE_DEAN("admin"),
    ROLE_INSTRUCTOR("Instructor"),
    ROLE_STUDENT("Student");

    public final String roleName;

    EnumRole(String name) {
        this.roleName = name;
    }

    public static EnumRole getRole(String name) {
        for (EnumRole enumRole : EnumRole.values()) {
            if (enumRole.roleName.equals(name)) {
                return enumRole;
            }
        }
        throw new RuntimeException("Role is not found.");
    }
}
