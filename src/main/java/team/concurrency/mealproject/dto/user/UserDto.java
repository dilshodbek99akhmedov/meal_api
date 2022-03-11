package team.concurrency.mealproject.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.GenericDto;


@Setter
@Getter
public class UserDto extends GenericDto {
    private String fullName;

    private String username;

    private String phoneNumber;

    private String department;

    private String position;

    private String password;

    @Builder(builderMethodName = "childBuilder")
    public UserDto(String id,
                   String fullName,
                   String username,
                   String phoneNumber,
                   String department,
                   String position,
                   String password) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
        this.password = password;
    }
}
