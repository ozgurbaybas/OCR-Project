package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Department;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.DepartmentResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.DepartmentRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.FacultyRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.RoleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    final DepartmentRepository departmentRepository;
    final FacultyRepository facultyRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public DepartmentServiceImpl
            (DepartmentRepository departmentRepository, FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository)
    {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
}