package com.gdtcore.securityservice.service;


import com.gdtcore.securityservice.model.dto.SignUpRequest;
import com.gdtcore.securityservice.model.entities.User;
import com.gdtcore.securityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public User createUser(SignUpRequest signUpRequest) {
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()))
                .build();
        return userRepository.save(user);
    }
}

