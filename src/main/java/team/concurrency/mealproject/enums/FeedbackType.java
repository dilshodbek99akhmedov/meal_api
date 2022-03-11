package team.concurrency.mealproject.enums;

import lombok.Getter;

@Getter
public enum FeedbackType {

    APPRECIATION("appreciation"),
    GUIDANCE("guidance"),
    ENCOURAGEMENT("encouragement"),
    NEGATIVE("negative");

    private final String name;


    FeedbackType(String name) {
        this.name = name;
    }

    public FeedbackType getByName(String name){
        for (FeedbackType value : FeedbackType.values()) {
            if(value.getName().equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }
}
