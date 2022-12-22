package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.FacultyRepository;
import org.springframework.stereotype.Service;

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
}
