package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

}
