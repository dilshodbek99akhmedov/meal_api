package team.concurrency.mealproject.dto.meal;

import lombok.Setter;
import team.concurrency.mealproject.dto.base.BaseGenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
public class MealCreateDto implements BaseGenericDto {

    @NotNull
    @Size(min = 10, max = 20, message = "10 tadan kop 20 dan kam")
    public String name;

    @Size(min = 5, message = "5 tadan kam bo'lsamasin")
    public String description;

    @NotBlank
    public String path;
}
