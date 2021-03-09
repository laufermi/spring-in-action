package com.example.demo.api;

import com.example.demo.api.mapper.UserApiMapper;
import com.example.demo.api.request.CreateUserRequest;
import com.example.demo.api.response.UserResponse;
import com.example.demo.application.UserApplicationService;
import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.application.command.DeleteUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserApplicationService userApplicationService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = userApplicationService.findAll().stream()
                .map(UserApiMapper.MAPPER::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserRequest request) {
        CreateUserCommand command = UserApiMapper.MAPPER.toCommand(request);
        userApplicationService.save(command);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String name) {
        userApplicationService.delete(DeleteUserCommand.of(name));
    }
}
