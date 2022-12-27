package com.ozgurbaybas.OnlineCourseRegistration.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String name;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    private String day;

    @ElementCollection
    private Set<Long> hour;

    public void setName() {
        this.name = course.getName()+"_schedule";
    }

}
