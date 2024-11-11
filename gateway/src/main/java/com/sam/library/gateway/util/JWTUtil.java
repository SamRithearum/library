package com.sam.library.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(Long studentId) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("Auth Details")
                .withClaim("studentId", studentId)
                .withIssuedAt(new Date())
                .withIssuer("SAM LIBRARY/PROJECT/SAM CORP")
                .sign(Algorithm.HMAC256(secret));
    }

    public Long validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Auth Details")
                .withIssuer("SAM LIBRARY/PROJECT/SAM CORP")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("studentId").asLong();
    }
}
