package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.UserDto;
import com.nt.entity.User;
import com.nt.jwt.JwtService;
import com.nt.request.CreateUserRequest;
import com.nt.service.IUserService;

@RestController
@RequestMapping("/jwt")
public class HomeController {
    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public ResponseEntity<String> showhome() {
        String msg = "Welcome Home";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registeruser(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.addUser(createUserRequest);
        return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> loginapi(@RequestParam String email, @RequestParam String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String msg = null;
        if (authentication.isAuthenticated())
            msg = jwtService.generateToken(authentication);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getallUserapi() {
        List<UserDto> userDtos = userService.getallusers();
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.ACCEPTED);
    }
}
