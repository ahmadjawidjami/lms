package com.tu.ziik.lms.model.admin.course;

import javax.persistence.*;

/**
 * Created by ahmadjawid on 12/23/16.
 */
@Entity
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
