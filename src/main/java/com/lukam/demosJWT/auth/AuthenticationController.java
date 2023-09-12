package com.lukam.demosJWT.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    /*
    private final PortalUserService portalUserService;
  
    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @NonNull AuthRequest authRequest) {
            log.info("Authentication request for user {} received!", authRequest.getUsername());
            return ResponseEntity.ok(portalUserService.authenticateUser(authRequest.getUsername(), authRequest.getPassword()));---ovako nekako želim
    }
    */

}
