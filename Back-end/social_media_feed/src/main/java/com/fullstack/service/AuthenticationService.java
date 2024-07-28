package com.fullstack.service;

import com.fullstack.Repository.UserRepository;
import com.fullstack.model.AuthenticationResponse;
import com.fullstack.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request){
       try {
           User user = new User();
           user.setFirstName(request.getFirstName());
           user.setLastName(request.getLastName());
           user.setUsername(request.getUsername());
           user.setPassword(passwordEncoder.encode(request.getPassword()));

           user.setRole(request.getRole());

           user = userRepository.save(user);

           String token = jwtService.generateToken(user);

           return new AuthenticationResponse(token);
       } catch (Exception e){
           return null;
       }
    }

    public AuthenticationResponse login(User request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );


            User user = userRepository.findByUsername(request.getUsername());
            String token = jwtService.generateToken(user);

            return new AuthenticationResponse(token);
        } catch (Exception e){
            return null;
        }
    }
}
