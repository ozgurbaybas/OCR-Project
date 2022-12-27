package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

public class SemesterActivateRequest {

    private Boolean isActive;
    private Long id;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
