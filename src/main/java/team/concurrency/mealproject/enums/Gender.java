package team.concurrency.mealproject.enums;

import lombok.Getter;


@Getter
public enum Gender {
    FEMALE("female"),
    MALE("male");

    private final String name;


    Gender(String name) {
        this.name = name;
    }


}
