package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.*;
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
    final FacultyRepository facultyRepository;
    final DepartmentRepository departmentRepository;

    public UserServiceImpl(FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserResponse addInstructorToFaculty(Long memberId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberId);
        Faculty faculty = facultyRepository.getById(memberRequest.getFacultyId());
        instructor.setFaculty(faculty);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

    public UserResponse removeInstructorFromFaculty(Long memberId) {

        User instructor = userRepository.getById(memberId);
        instructor.setFaculty(null);
        instructor = userRepository.save(instructor);
        return  new UserResponse(instructor);
    }


    public UserResponse addInstructorToDepartment(Long memberId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberId);
        Department department = departmentRepository.getById(memberRequest.getDepartmentId());
        instructor.setDepartment(department);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

}
