package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelectedCourseRequest {

    private List<Long> selectedCourseIds;

}
