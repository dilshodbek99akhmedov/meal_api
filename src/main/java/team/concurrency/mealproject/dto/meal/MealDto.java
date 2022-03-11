package team.concurrency.mealproject.dto.meal;

import lombok.Builder;
import team.concurrency.mealproject.dto.base.GenericDto;

public class MealDto extends GenericDto {
   public String name;
   public String description;
   public String path;

   @Builder(builderMethodName = "childBuilder")
   public MealDto(String id, String name, String description, String path) {
      super(id);
      this.name = name;
      this.description = description;
      this.path = path;
   }
}
