package com.example.agriculturesystem.security;






import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "212030317Mark131411";  // 用你自己的密钥替代

    private static final long EXPIRATION_TIME = 1000000;  // 10 天

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
    }

    public static String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public static boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }

    public static boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }
}
