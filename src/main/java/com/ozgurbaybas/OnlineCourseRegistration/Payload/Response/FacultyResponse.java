package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;

public class FacultyResponse {
    private Long id;
    private String name;
    private Long deanUserId;
    private String deanUsername;

    public FacultyResponse(Faculty faculty) {
        this.id = faculty.getId();
        this.name = faculty.getName();
        if (faculty.getDean() != null) {
            this.deanUserId = faculty.getDean().getId();
            this.deanUsername = faculty.getDean().getUsername();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeanUsername() {
        return deanUsername;
    }

    public void setDeanUsername(String deanUsername) {
        this.deanUsername = deanUsername;
    }

    public Long getDeanUserId() {
        return deanUserId;
    }

    public void setDeanUserId(Long deanUserId) {
        this.deanUserId = deanUserId;
    }
}