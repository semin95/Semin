package com.etf.ppis.lambda.telenash.security;

import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin (origins = "*", maxAge = 3600)
@RestController
@RequestMapping ("/token")
public class AuthenticationController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier ("userService")
    private UserService userDetailsService;

    @PostMapping (value = "/generate-token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginUser loginUser)
    {

        final Authentication authentication;
        try
        {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
        }
        catch (AuthenticationException e)
        {
            throw new com.etf.ppis.lambda.telenash.controller.errors.AuthenticationException("Login failed for user: " + loginUser.getUsername());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userDetailsService.getByUserName(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthToken(token));
    }
}
