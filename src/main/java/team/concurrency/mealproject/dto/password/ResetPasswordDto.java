package team.concurrency.mealproject.dto.password;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.GenericDto;

@Setter
@Getter
public class ResetPasswordDto extends GenericDto {

    private String oldPassword;

    private String newPassword;


    @Builder(builderMethodName = "childBuilder")
    public ResetPasswordDto(String id,
                            String oldPassword,
                            String newPassword) {
        super(id);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}