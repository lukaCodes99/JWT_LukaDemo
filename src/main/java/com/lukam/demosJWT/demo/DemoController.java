package com.lukam.demosJWT.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {


    @GetMapping("")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from secure");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> bok(){
        return ResponseEntity.ok("Hello admin");
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> bokic(){
        return ResponseEntity.ok("Hello user");
    }
    
}
