package com.gdtcore.securityservice.controller;

import com.gdtcore.securityservice.model.dto.SignUpRequest;
import com.gdtcore.securityservice.model.entities.User;
import com.gdtcore.securityservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gdtcore.securityservice.utils.EmailValidator.isValidEmailAddress;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignUpController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            if (signUpRequest.getEmail() == null || signUpRequest.getEmail().isEmpty()) {
                return new ResponseEntity<>("The e-mail address is mandatory.", HttpStatus.BAD_REQUEST);
            }

            if (!isValidEmailAddress(signUpRequest.getEmail())) {
                return new ResponseEntity<>("The e-mail address is invalid.", HttpStatus.BAD_REQUEST);
            }

            if (signUpRequest.getUsername() == null || signUpRequest.getUsername().isEmpty()) {
                return new ResponseEntity<>("Username is mandatory.", HttpStatus.BAD_REQUEST);
            }

            if (signUpRequest.getUsername().length() < 5) {
                return new ResponseEntity<>("User name must be at least 5 characters long.", HttpStatus.BAD_REQUEST);
            }

            if (signUpRequest.getPassword() == null || signUpRequest.getPassword().isEmpty()) {
                return new ResponseEntity<>("The password is mandatory.", HttpStatus.BAD_REQUEST);
            }

            if (signUpRequest.getPassword().length() < 8) {
                return new ResponseEntity<>("The password must be at least 8 characters long.", HttpStatus.BAD_REQUEST);
            }

            User createdUser = authenticationService.createUser(signUpRequest);
            String successMessage = String.format("User %s successfully created.", createdUser.getUsername());
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
