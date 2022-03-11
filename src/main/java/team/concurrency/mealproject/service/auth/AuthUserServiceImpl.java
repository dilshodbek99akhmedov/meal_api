package team.concurrency.mealproject.service.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.concurrency.mealproject.dto.password.ResetPasswordDto;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.dto.user.UserDto;
import team.concurrency.mealproject.dto.user.UserUpdateDto;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.enums.Role;
import team.concurrency.mealproject.exceptions.NotAuthorityException;
import team.concurrency.mealproject.exceptions.PasswordNotMatch;
import team.concurrency.mealproject.exceptions.UserNotFoundException;
import team.concurrency.mealproject.mapper.auth.AuthUserMapper;
import team.concurrency.mealproject.repository.auth.AuthUserRepository;
import team.concurrency.mealproject.service.base.AbstractService;
import team.concurrency.mealproject.validator.auth.AuthUserValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthUserServiceImpl extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator
        > implements AuthUserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    protected AuthUserServiceImpl(AuthUserMapper mapper, AuthUserValidator validator, AuthUserRepository repository, PasswordEncoder passwordEncoder) {
        super(mapper, validator, repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String create(UserCreateDto createDto) {
        User user = mapper.fromCreateDto(createDto);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        user.setStatus(-1);
        repository.save(user);
        return user.getId();
    }

    @Override
    public void delete(String id) {
        repository.softDeleteById(id);
    }

    @Override
    public Boolean update(UserUpdateDto updateDto) {
        User user = repository.findById(updateDto.getId()).get();
        user.setRole(updateDto.getRole());
        user.setDepartment(updateDto.getDepartment());
        user.setFullName(updateDto.getFullName());
        user.setPhoneNumber(updateDto.getPhoneNumber());
        user.setUsername(updateDto.getUsername());
        user.setPosition(updateDto.getPosition());
        repository.save(user);
        return true;
    }

    @Override
    public UserDto get(String id) {
        Optional<User> optionalUser = repository.findById(id);
        User user1 = optionalUser.orElseThrow(() -> {
            throw new UserNotFoundException("User not found");
        });
        return mapper.toDto(user1);
    }

    @Override
    public List<UserDto> getAll(String id) {
        return null;
    }


    @Override
    public List<UserDto> getAll() {
        return mapper.toDto(repository.findAll()
                .stream()
                .filter(user -> !user.isDeleted()).collect(Collectors.toList()));
    }

    public void resetPassword(ResetPasswordDto dto) {
        Optional<User> optionalUser = repository.findById(dto.id);
        User user = optionalUser.orElseThrow(() -> {
            throw new UserNotFoundException("User not found");
        });
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword()))
            throw new PasswordNotMatch("Password Not Match");

        repository.resetPassword(dto.getId(), passwordEncoder.encode(dto.getNewPassword()));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                .build();
    }

    @Override
    public User getByChatId(String chatId) {
        Long chatIdLong = Long.valueOf(chatId);
        return repository.getByChatId(chatIdLong);
    }


    public Boolean confirm(String id, String username) {
        User user = repository.findByUsername(username);
        User user1 = repository.findById(id).get();
        if (user.getRole().equalsIgnoreCase(Role.ADMIN.getName()) &&
                user.getDepartment().equalsIgnoreCase(user1.getDepartment())) {
            String id1 = user1.getId();
            repository.confirmUser(id1);
            return true;
        } else {
            throw new NotAuthorityException("You are not allowed to confirm users");
        }
    }

    @Override
    public User getByRole(String role) {
        return repository.getByRole(role);
    }

    @Override
    public User getAdministratorOfDepartment(String department) {
        return repository.getAdministratorByDepartment(department);
    }

    @Override
    public void acceptUser(String chatId) {
        repository.acceptUser(Long.valueOf(chatId));
    }
}
