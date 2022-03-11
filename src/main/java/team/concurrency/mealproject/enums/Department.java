package team.concurrency.mealproject.enums;

import lombok.Getter;

@Getter
public enum Department {

    SALES("sales"),
    KIDS("kids"),
    ENGLISH("english"),
    ACADEMIC("academic"),
    HR("hr");

    private final String name;

    Department(String name) {
        this.name = name;
    }

    public Department getByName(String name) {
        for (Department value : Department.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }


}
