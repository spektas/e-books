package com.getir.ebooks.security.controller;

import com.getir.ebooks.security.entity.User;
import com.getir.ebooks.security.service.AuthenticationProviderService;
import com.getir.ebooks.security.service.JpaUserDetailsService;
import com.getir.ebooks.security.jwt.JwtUtil;
import com.getir.ebooks.security.model.AuthenticationRequest;
import com.getir.ebooks.security.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationProviderService authenticationManager;

    private final JpaUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("users/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("users/createdumpusers")
    public ResponseEntity<?> createDumpUsers() {

        User user = userDetailsService.save();
        return ResponseEntity.ok(user);
    }
}
