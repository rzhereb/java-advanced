package com.oktetweb.springjavaadvanced.controller;

import com.oktetweb.springjavaadvanced.dtos.AuthenticationRequest;
import com.oktetweb.springjavaadvanced.dtos.AuthenticationResponse;
import com.oktetweb.springjavaadvanced.model.User;
import com.oktetweb.springjavaadvanced.service.IUserService;
import com.oktetweb.springjavaadvanced.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        String token = jwtService.generateToken(authenticationRequest.getUsername());
        return new AuthenticationResponse(token);
    }
}
