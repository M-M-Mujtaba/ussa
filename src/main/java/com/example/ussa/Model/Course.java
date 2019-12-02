package com.example.ussa.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 60)
    @Getter
    @Setter
    private String name;

    @NotBlank
    @NaturalId
    @Column(length = 5)
    @Getter
    @Setter
    private String code;


    @Column(length = 1)
    @Getter
    @Setter
    private char Sec;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Course_Instructors",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> Teachers = new ArrayList<>();


    @OneToMany(targetEntity = Course_Review.class)
    @Column
    @Getter
    @Setter
    private List Course_Review = new ArrayList();





    public Course() {

    }

    public Course(String name, String code, char s, List<Instructor> ins, List reviews) {
        this.name = name;
        this.code = code;
        this.Sec = s;
        this.Teachers = ins;
        this.Course_Review = reviews;
    }

    public float getAvgRating(){
        float sum = 0;
        float avg = 0;

        for ( Object r: Course_Review
             ) {
            Review theR = (Review) r;
            sum+= theR.rating;

        }
        avg = sum / Course_Review.size();
        return avg;
    }

    public void addReview(Review r){
        this.getCourse_Review().add(r);
    }

    public void removeReview(Review r){this.getCourse_Review().remove(r);}

    public void addInstructor(Instructor inst){this.Teachers.add(inst);}

    public void removeInstructor(Instructor inst){this.Teachers.remove(inst);}


}