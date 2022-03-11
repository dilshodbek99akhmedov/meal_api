package team.concurrency.mealproject.service.auth;

import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.dto.user.UserDto;
import team.concurrency.mealproject.dto.user.UserUpdateDto;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.service.base.GenericCrudService;

public interface AuthUserService extends GenericCrudService<
        UserDto,
        UserCreateDto,
        UserUpdateDto,
        String
        > {

    User getByChatId(String chatId);

    User getByRole(String role);

    User getAdministratorOfDepartment(String department);

    void acceptUser(String target);

}
