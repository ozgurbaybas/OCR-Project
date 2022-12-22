package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public FacultyResponse addFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.save(new Faculty(facultyRequest.getName()));
        return new FacultyResponse(faculty.getId(), faculty.getName());
    }

    @Override
    public List<FacultyResponse> listFaculties() {
        List<Faculty> faculties = facultyRepository.findAllByIsActive(true);
        return faculties.stream().map(faculty -> new FacultyResponse(faculty.getId(), faculty.getName())).collect(Collectors.toList());
    }

    @Override
    public FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.getById(facultyId);
        faculty.setName(facultyRequest.getName());
        facultyRepository.save(faculty);
        return new FacultyResponse(faculty.getId(), faculty.getName());
    }
}
