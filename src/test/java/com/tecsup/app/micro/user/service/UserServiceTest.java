package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testCreateUser() {
        Long NEW_ID = 1L;

        // ---------- DTO enviado desde el controller ----------
        User input = User.builder()
                .id(null)
                .name("Eder")
                .email("eder@gmail.com")
                .phone("999999999")
                .address("Av. Lima 123")
                .build();

        // ---------- Entity antes de guardar ----------
        UserEntity entityToSave = userMapper.toEntity(input);

        // ---------- Entity que el repositorio devolverá (mock) ----------
        UserEntity savedEntity = new UserEntity(
                NEW_ID,
                "Eder",
                "eder@gmail.com",
                "999999999",
                "Av. Lima 123"
        );

        // mock del repository
        when(userRepository.save(entityToSave)).thenReturn(savedEntity);

        // ---------- Ejecutar servicio ----------
        User result = userService.createUser(input);

        // ---------- Validaciones ----------
        assertNotNull(result);
        assertEquals(NEW_ID, result.getId());
        assertEquals("Eder", result.getName());
        assertEquals("eder@gmail.com", result.getEmail());
        assertEquals("999999999", result.getPhone());
        assertEquals("Av. Lima 123", result.getAddress());

    }
}