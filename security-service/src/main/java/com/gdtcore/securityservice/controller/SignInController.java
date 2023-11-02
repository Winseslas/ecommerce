package com.gdtcore.securityservice.controller;

import com.gdtcore.securityservice.model.dto.AuthenticationRequest;
import com.gdtcore.securityservice.model.dto.AuthenticationResponse;
import com.gdtcore.securityservice.service.jwt.UserDetailsService;
import com.gdtcore.securityservice.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/signing")
@RequiredArgsConstructor
public class SignInController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials");
        } catch (DisabledException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }
}
