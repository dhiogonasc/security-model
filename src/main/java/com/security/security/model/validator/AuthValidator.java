package com.security.security.model.validator;

import com.security.security.model.domain.User;
import com.security.security.model.exception.BusinessException;
import com.security.security.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Component
public class AuthValidator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void validateEmailExists(String email) {

        if(userRepository.existsByEmail(email)){
            throw new BusinessException("email", "Email já cadastrado", CONFLICT);
        }
    }

    public User validateEmailRegistered(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("auth", "Usuário ou senha incorretos", BAD_REQUEST));
    }

    public void validatePassword(String password, String hash) {

        if (!encoder.matches(password, hash)) {
            throw new BusinessException("auth", "Usuário ou senha incorretos", BAD_REQUEST);
        }
    }
}
