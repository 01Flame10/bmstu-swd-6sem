package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.exception.UserNotFoundException;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User retrieveUser(String uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }
}
