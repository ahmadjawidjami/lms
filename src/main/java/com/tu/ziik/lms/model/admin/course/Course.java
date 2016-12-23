package com.tu.ziik.lms.model.admin.course;



import javax.persistence.*;
import javax.persistence.Id;

/**
 * Created by ahmadjawid on 12/23/16.
 */

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;


    private String content;

    @Version
    private Integer version;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
