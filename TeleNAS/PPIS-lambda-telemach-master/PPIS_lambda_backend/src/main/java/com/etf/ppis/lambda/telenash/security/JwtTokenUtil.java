package com.etf.ppis.lambda.telenash.security;

import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import static com.etf.ppis.lambda.telenash.configuration.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.etf.ppis.lambda.telenash.configuration.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable
{
    @Autowired
    UserService userService;

    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user)
    {
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject)
    {

        Claims claims = Jwts.claims().setSubject(subject);
        /*claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        */
        String role = userService.getByUserName(subject).getUserRole().getName();
        claims.put("role", new SimpleGrantedAuthority(role));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("Telenash")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

}

