package team.concurrency.mealproject.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.GenericDto;


@Setter
@Getter
public class UserUpdateDto extends GenericDto {
    public String fullName;

    public String username;

    public String phoneNumber;

    public String role;

    public String department;

    public String position;

    @Builder(builderMethodName = "childBuilder")
    public UserUpdateDto(String id,
                         String fullName,
                         String username,
                         String phoneNumber,
                         String role,
                         String department,
                         String position) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.department = department;
        this.position = position;
    }
}
