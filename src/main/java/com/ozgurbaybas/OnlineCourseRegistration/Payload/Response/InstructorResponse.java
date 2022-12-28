package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InstructorResponse {
    private Long id;
    private String name;

//    public InstructorResponse(User user) {
//        this.id = user.getId();
//        this.name = user.getUsername();
//    }
}
