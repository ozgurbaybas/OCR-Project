package com.ozgurbaybas.OnlineCourseRegistration.Repository;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByIsActive(boolean isActive);
    List<Course> findAllByIsApproved(boolean isApproved);

    Course getById(Long courseId);

    Course save(Course newCourse);
}
