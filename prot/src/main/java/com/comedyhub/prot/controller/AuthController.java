package com.comedyhub.prot.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.comedyhub.prot.dto.UserDtoResponse;
import com.comedyhub.prot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.comedyhub.prot.dto.LoginRequest;
import com.comedyhub.prot.dto.TokenResponse;
import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDtoCreate userDto) {
        UserDtoResponse userDtoResponse = userService.createUser(userDto);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = createToken(userDtoResponse.getId());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception{
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            UserDtoResponse userDTO = new UserDtoResponse();

            if(authentication.isAuthenticated()) {
                    userDTO = userService.getUserByUsername(loginRequest.getUsername());
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = createToken(userDTO.getId());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String createToken(Long id) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(30, ChronoUnit.DAYS))
            .subject(id.toString())
            .claim("scope", "ROLE_USER")
            .build();
        JwtEncoderParameters encoderParams = JwtEncoderParameters.from(claims);
        Jwt jwt = this.jwtEncoder.encode(encoderParams);
        String tokenValue = jwt.getTokenValue();
		return tokenValue;
    }

}
