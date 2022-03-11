package team.concurrency.mealproject.enums;

import lombok.Getter;

@Getter
public enum Role {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    MANAGER("manager"),
    USER("user");

    private final String name;


    Role(String name) {
        this.name = name;
    }

    public Role getByName(String name) {
        for (Role value : Role.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;

    }


}
