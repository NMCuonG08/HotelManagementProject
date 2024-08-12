    package com.example.HotelManagerment.Security.jwt;

    import com.example.HotelManagerment.User.User;
    import com.example.HotelManagerment.User.UserRepository;
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;

    import java.security.Key;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.function.Function;

    @Service
    @RequiredArgsConstructor
    public class JWTService {

            @Value("${spring.jwt.secret}")
            private String JWT_SECRET ;
            @Value("${spring.jwt.jwtExpirationTime}")
            private long JWT_EXPIRATION_TIME ;

            public String getGeneratedToken(String email) {
                Map<String, Object> claims = new HashMap<>();
                return generatedTokenForUser(claims, email);
            }

            private String generatedTokenForUser(Map<String, Object> claims, String email) {
                return Jwts.builder()
                        .setClaims(claims)
                        .setSubject(email)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                        .signWith(getSignKey(), SignatureAlgorithm.HS256)
                        .compact();
            }

            private Key getSignKey() {
                byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
                return Keys.hmacShaKeyFor(keyBytes);
            }

            public String extractUsernameFromToken(String theToken){
                return extractClaim(theToken, Claims::getSubject);
            }

            public Date extractExpirationTimeFromToken(String token){
                return extractClaim(token, Claims::getExpiration);
            }

            public Boolean validToken(String token , User user){
                final String username = extractUsernameFromToken(token);
                return (username.equals(user.getEmail()) && !isTokenExpired(token));
            }

            private <T> T  extractClaim(java.lang.String token, Function<Claims, T> claimsResolver) {
                final Claims claims = extractAllClaimsToken(token);
                return claimsResolver.apply(claims);
            }

            private Claims extractAllClaimsToken(String token) {
                return Jwts.parserBuilder()
                        .setSigningKey(getSignKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }

            private  boolean isTokenExpired(String token){
                return extractExpirationTimeFromToken(token).before(new Date());
            }
    }
