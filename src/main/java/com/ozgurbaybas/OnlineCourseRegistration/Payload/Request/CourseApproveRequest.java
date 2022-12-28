package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import javax.validation.constraints.NotNull;

public class CourseApproveRequest {

    @NotNull
    private boolean approve;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

}