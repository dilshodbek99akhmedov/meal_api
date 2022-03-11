package team.concurrency.mealproject.dto.meal;

import lombok.Builder;
import team.concurrency.mealproject.dto.base.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MealUpdateDto extends GenericDto {

    @Size(min = 10,max = 20,message = "10 tadan kop 20 dan kam")
    public String name;

    @Size(min = 5,message = "5 tadan kam bolsamasin")
    public String description;

    @NotBlank
    public String path;

    @Builder(builderMethodName = "childBuilder")
    public MealUpdateDto(String id, String name, String description, String path) {
        super(id);
        this.name = name;
        this.description = description;
        this.path = path;
    }
}
