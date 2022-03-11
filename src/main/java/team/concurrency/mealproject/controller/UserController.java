package team.concurrency.mealproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.concurrency.mealproject.dto.password.ResetPasswordDto;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.dto.user.UserDto;
import team.concurrency.mealproject.dto.user.UserUpdateDto;
import team.concurrency.mealproject.service.auth.AuthUserServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthUserServiceImpl service;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") String id) {
        UserDto userDto = service.get(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @PutMapping("/api/users/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(name = "id") String id, @RequestBody UserUpdateDto dto) {
        dto.setId(id);
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @PutMapping("resetPassword/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable(name = "id") String id, @RequestBody ResetPasswordDto dto){
        service.resetPassword(dto);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> create(@RequestBody UserCreateDto createDto) {
        String id = service.create(createDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/users/confirm/{id}")
    public ResponseEntity<Boolean> confirmUser(@PathVariable(name = "id") String id){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = object.toString();
        Boolean result = service.confirm( id, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
