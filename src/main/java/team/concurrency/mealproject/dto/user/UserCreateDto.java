package team.concurrency.mealproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.BaseGenericDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto implements BaseGenericDto {
    public String fullName;

    public String username;

    public String password;

    public String phoneNumber;

    public String department;

    public String gender;

    public String role;

    public String position;

    public String chatId;
}
