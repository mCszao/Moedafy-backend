package com.moedafy.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.moedafy.api.domain.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generateToken(User user){
        try {
            Algorithm alg = Algorithm.HMAC256("randomKey");
            return JWT.create()
                    .withIssuer("moedafy-auth")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpireDate())
                    .sign(alg);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na geração do JWT", exception);
        }
    }

    public String validateJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256("randomKey");
        try {
            return JWT.require(algorithm)
                    .withIssuer("moedafy-auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public Instant generateExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
