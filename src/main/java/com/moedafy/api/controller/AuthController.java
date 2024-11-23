package com.moedafy.api.controller;

import com.moedafy.api.core.dto.BaseResponse;
import com.moedafy.api.domain.dto.ResponseLoginDTO;
import com.moedafy.api.domain.dto.UserLoginDTO;
import com.moedafy.api.domain.dto.UserRegisterDTO;
import com.moedafy.api.domain.entity.User;
import com.moedafy.api.services.MyUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MyUserDetailsService authService;

    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/signIn")
    public ResponseEntity<BaseResponse<ResponseLoginDTO>> signIn(@RequestBody @Valid UserLoginDTO body){
        var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(body.username(), body.password()));
        var userLogin = authService.login(body, (User) authentication.getPrincipal());
        return ResponseEntity.ok(new BaseResponse<>(true, userLogin));
    }

    @PostMapping("/signUp")
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody @Valid UserRegisterDTO body){
        authService.register(body);
        return ResponseEntity.ok(new BaseResponse<>(true, "Cadastro realizado com sucesso!"));
    }
}
