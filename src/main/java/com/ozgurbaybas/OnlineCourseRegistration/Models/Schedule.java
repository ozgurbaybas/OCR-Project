package com.ozgurbaybas.OnlineCourseRegistration.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {

    public Schedule() {

    }

    public Schedule (Course course, EnumDay day, Long hour) {
        this.name = course.getName()+"_schedule_"+day;
        this.course = course;
        this.day = day;
        this.hour = hour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String name;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumDay day;

    @NotEmpty
    private Long hour;

    @ManyToOne
    private User instructor;

    @ManyToOne
    private Semester semester;

}
