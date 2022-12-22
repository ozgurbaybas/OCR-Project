package com.ozgurbaybas.OnlineCourseRegistration.Repository;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
