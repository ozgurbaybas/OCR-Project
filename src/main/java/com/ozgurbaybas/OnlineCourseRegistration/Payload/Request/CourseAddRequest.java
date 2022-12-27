package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import javax.validation.constraints.NotBlank;

public class CourseAddRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
