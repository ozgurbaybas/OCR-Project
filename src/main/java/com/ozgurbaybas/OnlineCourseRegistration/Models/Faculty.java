package com.ozgurbaybas.OnlineCourseRegistration.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "faculties",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Faculty extends BaseModel {

    @NotBlank
    @Size(max = 50)
    private String name;

    public Faculty() {
    }

    public Faculty(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
