package com.ozgurbaybas.OnlineCourseRegistration.Repository;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Department;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Semester;
import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllBySemesterAndCourse_Department(Semester semester, Department department);
    List<Schedule> findAllByCourse_Students (User student);
}