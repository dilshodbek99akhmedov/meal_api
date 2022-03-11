package team.concurrency.mealproject.enums;

import lombok.Getter;

@Getter
public enum Position {

    ADMINISTRATOR("administrator"),
    TEACHER("teacher"),
    ASSISTANT("assistant"),
    EMPLOYEE("employee");

    private final String name;

    Position(String name) {
        this.name = name;
    }

    public Position getByName(String name) {
        for (Position value : Position.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }


}
