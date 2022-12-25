package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import javax.validation.constraints.NotNull;

public class CourseApproveRequest {

    @NotNull
    private boolean approve;

    @NotNull
    private Long approvedOrRejectedById;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public Long getApprovedOrRejectedById() {
        return approvedOrRejectedById;
    }

    public void setApprovedOrRejectedById(Long approvedOrRejectedById) {
        this.approvedOrRejectedById = approvedOrRejectedById;
    }
}
