package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.repository.mapper.UserRepoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepo userRepo;

    @Override
    public Optional<User> findByName(String name) {
        return userRepo.findFirstByNameIgnoreCase(name)
                .map(UserRepoMapper.MAPPER::toDomain);
    }

    @Override
    public void save(User user) {
        userRepo.save(UserRepoMapper.MAPPER.toPo(user));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll().stream()
                .map(UserRepoMapper.MAPPER::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(User user) {
        userRepo.deleteByNameIgnoreCase(user.getName());
    }
}
