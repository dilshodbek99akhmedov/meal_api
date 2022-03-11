package team.concurrency.mealproject.mapper.auth;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.dto.user.UserDto;
import team.concurrency.mealproject.dto.user.UserUpdateDto;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.mapper.base.AbstractMapper;

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends AbstractMapper<User, UserDto, UserCreateDto, UserUpdateDto> {


}
