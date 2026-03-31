package com.security.security.model.service;

import com.security.security.model.controller.request.LoginRequestDTO;
import com.security.security.model.controller.request.UserRequestDTO;
import com.security.security.model.controller.response.LoginResponseDTO;
import com.security.security.model.domain.User;
import com.security.security.model.repository.UserRepository;
import com.security.security.model.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthValidator authValidator;
    private final BCryptPasswordEncoder encoder;
    private final JwtEncoder jwtEncoder;

    public void register(UserRequestDTO request) {

        authValidator.validateEmailExists(request.email());

        User user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = authValidator.validateEmailRegistered(request.email());
        authValidator.validatePassword(request.password(), user.getPassword());

        Instant now = Instant.now();
        long expiresIn = 3600L;

        var claims = JwtClaimsSet.builder()
                .issuer("auth")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(accessToken, expiresIn);
    }
}
