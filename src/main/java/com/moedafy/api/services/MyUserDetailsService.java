package com.moedafy.api.services;

import com.moedafy.api.domain.dto.ResponseLoginDTO;
import com.moedafy.api.domain.dto.UserLoginDTO;
import com.moedafy.api.domain.dto.UserRegisterDTO;
import com.moedafy.api.domain.entity.User;
import com.moedafy.api.domain.mapper.UserMapper;
import com.moedafy.api.infra.security.TokenService;
import com.moedafy.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String input) {
        var userHasEmail = userRepo.findByEmail(input) != null;
        if(userHasEmail){
            return userRepo.findByEmail(input);
        };
        return userRepo.findByUsername(input).orElseThrow(() -> new RuntimeException("Usuário ou senha incorretos"));
    }

    public ResponseLoginDTO login(UserLoginDTO dto, User authUser){
        var token = tokenService.generateToken(authUser);
        return new ResponseLoginDTO(dto.username(),token);
    }

    @Transactional
    public void register(UserRegisterDTO dto){
        if(!(userRepo.findByUsername(dto.username()).isEmpty())) throw new RuntimeException("Esse usuário já existe");
        if(userRepo.findByEmail(dto.email() == null ? "Não cadastrado" : dto.email()) != null) throw new RuntimeException("Esse email já existe");
        User user = mapper.create(dto);
        userRepo.save(user);
    }

}