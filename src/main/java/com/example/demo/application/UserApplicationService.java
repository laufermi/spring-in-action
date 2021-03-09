package com.example.demo.application;

import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.application.command.DeleteUserCommand;
import com.example.demo.application.exception.DuplicateUsernameException;
import com.example.demo.application.exception.UserNotFoundException;
import com.example.demo.application.mapper.UserAppMapper;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    @Transactional(rollbackOn = Exception.class)
    public void save(CreateUserCommand command) {
        User user = UserAppMapper.MAPPER.toDomain(command);
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new DuplicateUsernameException(user.getName());
        }
        userRepository.save(user);
    }


    @Transactional(rollbackOn = Exception.class)
    public void delete(DeleteUserCommand command) {
        User user = UserAppMapper.MAPPER.toDomain(command);
        userRepository.delete(user);
    }

}
