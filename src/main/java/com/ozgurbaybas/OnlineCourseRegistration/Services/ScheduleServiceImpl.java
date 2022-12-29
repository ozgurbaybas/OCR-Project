package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.*;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.ScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduleResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduledCourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.ScheduleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.SemesterRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Security.Services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final SemesterRepository semesterRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(SemesterRepository semesterRepository, UserRepository userRepository, CourseRepository courseRepository, ScheduleRepository scheduleRepository) {
        this.semesterRepository = semesterRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponse assignCourseToSchedule(ScheduleRequest scheduleRequest) {
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);
        User selectedInstructor = userRepository.getById(scheduleRequest.getInstructorId());
        Course selectedCourse = courseRepository.getById(scheduleRequest.getCourseId());
        EnumDay selectedDay = EnumDay.getDay(scheduleRequest.getDay());
        //todo check schedule conflict
        Schedule newSchedule = Schedule.builder().semester(activeSemester).instructor(selectedInstructor).course(selectedCourse).day(selectedDay).hour(scheduleRequest.getHour()).build();
        Schedule savedSchedule = scheduleRepository.save(newSchedule);
        return new ScheduleResponse(savedSchedule);
    }

    @Override
    public List<ScheduledCourseResponse> getOpenCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();
        User student = userRepository.getById(studentId);
        Department department = student.getDepartment();
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);
        List<Schedule> schedules = scheduleRepository.findAllBySemesterAndCourse_Department(activeSemester, department);
        Map<Course, List<Schedule>> scheduleMap = schedules.stream().collect(Collectors.groupingBy(Schedule::getCourse));
        List<ScheduledCourseResponse> scheduledCourseResponses = new ArrayList<>();
        for (Course course : scheduleMap.keySet()) {
            List<Schedule> groupedSchedules = scheduleMap.get(course);
            scheduledCourseResponses.add(new ScheduledCourseResponse(course, groupedSchedules));
        }
        return scheduledCourseResponses;
    }

    @Override
    public ScheduledCourseResponse registerToCourse(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();
        User student = userRepository.getById(studentId);
        Course selectedCourse = courseRepository.getById(courseId);
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(selectedCourse);
        student.setStudentsCourses(courseSet);
        userRepository.save(student);
        //selectedCourse.getStudents().add(student);
        //courseRepository.save(selectedCourse);
        List<Schedule> scheduleList = new ArrayList<>(selectedCourse.getSchedules());
        return new ScheduledCourseResponse(selectedCourse,scheduleList);
    }

}