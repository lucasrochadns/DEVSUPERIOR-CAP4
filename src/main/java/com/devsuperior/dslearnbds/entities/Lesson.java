package com.devsuperior.dslearnbds.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_lesson")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lesson implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer poss;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;

    @ManyToMany
    @JoinTable(name = "tb_lesson_done",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns =
                    {
                            @JoinColumn(name ="user_id"),
                            @JoinColumn(name="offer_id")
                    })
    private Set<Enrollment> enrollmentsDone = new HashSet<>();

    public Lesson(){}
    public Lesson(Long id, String title, Integer poss, Section section) {
        this.id = id;
        this.title = title;
        this.poss = poss;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPoss() {
        return poss;
    }

    public void setPoss(Integer poss) {
        this.poss = poss;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setEnrollmentsDone(Set<Enrollment> enrollmentsDone) {
        this.enrollmentsDone = enrollmentsDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id.equals(lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}