package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.*;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentMemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.DepartmentRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.FacultyRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.RoleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

}
