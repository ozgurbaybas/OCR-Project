package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import javax.validation.constraints.NotNull;

public class CourseInstructorAssignRequest {

    @NotNull
    private Long instructorId;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
}