package team.concurrency.mealproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.enums.Department;
import team.concurrency.mealproject.enums.Gender;
import team.concurrency.mealproject.enums.Position;
import team.concurrency.mealproject.enums.Role;
import team.concurrency.mealproject.repository.auth.AuthUserRepository;

import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class MealProjectApplication {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MealProjectApplication(AuthUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(MealProjectApplication.class, args);
    }

  //  @Bean
    CommandLineRunner run() {
        return args -> {
            userRepository.save(User.builder()
                    .id(UUID.randomUUID().toString())
                    .fullName("admin")
                    .username("admin")
                    .password(passwordEncoder.encode("123"))
                    .phoneNumber("+998998887766")
                    .department(Department.ACADEMIC.name())
                    .role(Role.SUPER_ADMIN.name())
                    .gender(Gender.MALE.name())
                    .position(Position.ADMINISTRATOR.name())
                    .build());
        };
    }


}
