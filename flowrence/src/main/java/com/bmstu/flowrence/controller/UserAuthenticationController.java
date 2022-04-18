package com.bmstu.flowrence.controller;

import com.bmstu.flowrence.auth.jwt.service.JwtTokenService;
import com.bmstu.flowrence.dto.request.UserLoginCredentialsDto;
import com.bmstu.flowrence.dto.request.UserRegisterCredentialsDto;
import com.bmstu.flowrence.dto.response.JwtTokenDto;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.dto.RegisterCredentialsToUserMapper;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthenticationController {

    final private UserRepository userRepository;
    final private JwtTokenService jwtTokenService;
    final private RegisterCredentialsToUserMapper registerCredentialsToUserMapper;

    // TODO: extract all these stuff to service layer

    @PostMapping(value = "/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> createUser(@RequestBody UserRegisterCredentialsDto credentials) {
        log.info("Creating user {}", credentials);
        try {
            Optional<User> userOptional = userRepository.findByEmail(credentials.getEmail());

            if (userOptional.isPresent())
                return new ResponseEntity<>(HttpStatus.CONFLICT);

            User user = registerCredentialsToUserMapper.mapSourceToDestination(credentials);

            // TODO: add BCrypt / wrapper (decorator on repository)
            user = userRepository.save(user);

            return new ResponseEntity<>(JwtTokenDto.builder()
                    .jwtToken(jwtTokenService.createToken(user))
                    .build(), HttpStatus.OK);
//            return new ResponseEntity<>("{\"jwtToken\": \"" + jwtTokenService.createToken(user) + "\"}", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error creating user", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserLoginCredentialsDto credentials) {
        log.debug("Logging in with {}", credentials);
        try {
            Optional<User> userOptional = userRepository.findByEmail(credentials.getEmail());

            return userOptional
                    .filter(user -> user.getPassword().equals(credentials.getPassword())) // TODO: add BCrypt / wrapper (decorator on repository)
                    .map(user -> new ResponseEntity<>(JwtTokenDto.builder()
                            .jwtToken(jwtTokenService.createToken(user))
                            .build(), HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
