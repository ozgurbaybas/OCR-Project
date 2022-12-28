package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.*;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.DepartmentResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    final DepartmentRepository departmentRepository;
    final FacultyRepository facultyRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final CourseRepository courseRepository;

    public DepartmentServiceImpl
            (DepartmentRepository departmentRepository, FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository)
    {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        Faculty faculty = facultyRepository.getById(departmentRequest.getFacultyId());
        Department newDepartment = new Department(departmentRequest.getName(), faculty);
        Department department = departmentRepository.save(newDepartment);
        return new DepartmentResponse(department);
    }

    @Override
    public List<DepartmentResponse> listDepartments() {
        List<Department> departments = departmentRepository.findAllByIsActive(true);
        return departments.stream().map(DepartmentResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentResponse> listDepartmentsByFaculty(Long facultyId) {
        List<Department> departments = departmentRepository.findAllByFacultyIdAndIsActive(facultyId,true);
        return departments.stream().map(DepartmentResponse::new).collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.getById(departmentId);
        department.setName(departmentRequest.getName());
        departmentRepository.save(department);
        return new DepartmentResponse(department);
    }

    @Override
    public Void deleteDepartmentById(Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        departmentRepository.deleteById(departmentId);
        return null;
    }

    @Override
    public UserResponse addInstructorToDepartment(Long departmentId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberRequest.getMemberId());
        Department department = departmentRepository.getById(departmentId);
        Long facultyId = instructor.getFaculty().getId();
        Long departmentFacultyId = department.getFaculty().getId();
        if(Objects.equals(facultyId, departmentFacultyId)) {
            instructor.setDepartment(department);
            instructor = userRepository.save(instructor);
        }
        else throw new RuntimeException("Department not found in this faculty");

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

    @Override
    public CourseResponse addCourseRequest(Long departmentId, CourseAddRequest courseAddRequest) {
        Department department = departmentRepository.getById(departmentId);
        Course newCourse = new Course(courseAddRequest.getName(), department);
        Course course = courseRepository.save(newCourse);
        return new CourseResponse(course);
    }

    @Override
    public UserResponse addStudentToDepartment(Long departmentId, MemberRequest memberRequest) {
        User student = userRepository.getById(memberRequest.getMemberId());
        Department department = departmentRepository.getById(departmentId);
        Faculty faculty = department.getFaculty();
        student.setDepartment(department);
        student.setFaculty(faculty);
        student = userRepository.save(student);
        Role studentRole = roleRepository.findByName(EnumRole.ROLE_STUDENT).orElseThrow(() -> new RuntimeException("Role is not found."));
        student.getRoles().add(studentRole);
        userRepository.save(student);
        return new UserResponse(student);
    }


}