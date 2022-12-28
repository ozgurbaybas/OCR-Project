package com.ozgurbaybas.OnlineCourseRegistration.Repository;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByIsActive(boolean isActive);
    List<Course> findAllByIsApproved(boolean isApproved);

    @Query(value = "SELECT item FROM Course item WHERE item.id IN (:courseIds) ")
    Set<Course> getSelectedCourses(@Param("courseIds") List<Long> courseIds);

    List<Course> findAllBySemesters_IsActive(boolean isActive);


    Course getById(Long courseId);
    Course save(Course newCourse);
}
