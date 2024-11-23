package com.moedafy.api.domain.mapper;

import com.moedafy.api.domain.dto.UserRegisterDTO;
import com.moedafy.api.domain.entity.User;
import com.moedafy.api.domain.entity.Wallet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User create(UserRegisterDTO dto) {
        User entity = new User();
        entity.setUsername(dto.username());
        String encryptedPass = new BCryptPasswordEncoder().encode(dto.password());
        entity.setPassword(encryptedPass);
        entity.setEmail(dto.email());
        entity.setName(dto.name());
        entity.setWallet(new Wallet());
        return entity;
    }
}
