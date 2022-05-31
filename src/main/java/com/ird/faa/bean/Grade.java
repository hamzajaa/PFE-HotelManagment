package com.ird.faa.bean;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @SequenceGenerator(name = "grade_seq", sequenceName = "grade_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    private Long id;

    @Column(length = 500)
    private String name;
    @Column(length = 500)
    private String code;


    public Grade() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id != null && id.equals(grade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

