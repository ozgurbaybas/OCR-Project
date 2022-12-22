package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import javax.validation.constraints.NotNull;

public class FacultyDeanAssignmentRequest {
    @NotNull
    private Long deanId;

    public Long getDeanId() {
        return deanId;
    }

    public void setDeanId(Long deanId) {
        this.deanId = deanId;
    }
}
