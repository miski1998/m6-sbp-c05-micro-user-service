package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;


    public User getUserById(Long id){

        UserEntity entity = userRepository.findById(id).orElse(null);

        return  mapper.toDomain(entity);
    }

    public List<User> getAllUsers() {

        List<UserEntity> entities = userRepository.findAll();
        return this.mapper.toDomain(entities);

    }

    public User createUser(User user) {
        // Convertir el DTO a Entity
        UserEntity entity = mapper.toEntity(user);

        // Guardar en BD
        UserEntity saved = userRepository.save(entity);

        // Retornar DTO
        return mapper.toDomain(saved);
    }

    //comentario para jenkins
}
