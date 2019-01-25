package com.iswAcademy.Voucherz.security.util;

import com.iswAcademy.Voucherz.security.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;


    public String generateToken(Authentication authentication){
        //downcasted
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Claims claims = Jwts.claims().setSubject(userPrincipal.getEmail());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setSubject(userPrincipal.getId().toString())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();

    }

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException ex){
            logger.error("Invalid JWT signature");
        }catch(MalformedJwtException ex){
            logger.error("Invalid Jwt token");
        }catch(ExpiredJwtException ex){
            logger.error("Expired Jwt Token");
        }catch(UnsupportedJwtException ex){
            logger.error("Unsupported Jwt token");
        }catch(IllegalArgumentException ex){
            logger.error("JWT claims string is empty");
        }
        return false;
    }







}