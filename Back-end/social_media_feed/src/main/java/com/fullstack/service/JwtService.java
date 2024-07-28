package com.fullstack.service;

import com.fullstack.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Service
public class JwtService {

    private final String Secret_key = "88500DA19737DF71B93EBE8CA5111A0872D3AB0CEAA965C5EB34315EB3B3B1D48DC490E1190C3F6F0B3DC65CEC4F0F63BB078A48699CC60B8D3D4E87E9C44A4A";

    public String extractUsername(String token){

        return extractClaims(token, Claims:: getSubject);
    }

    public boolean isTokenValid(String token, UserDetails  userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims (String token){
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private final static long ExpirationTime = 86400000;  // means 24 hour

    public String generateToken(UserDetails userDetails){
        String token = Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ExpirationTime ))
                .signWith(getSigningKey())
                .compact();

        return token;
    }

    public String refreshToken(HashMap<String , Object> claims , UserDetails userDetails){
        String token = Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ExpirationTime ))
                .signWith(getSigningKey())
                .compact();

        return token;
    }

    private SecretKey getSigningKey(){
        byte[] keyByte = Decoders.BASE64.decode(Secret_key);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
