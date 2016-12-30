package com.tu.ziik.lms.model;



import com.tu.ziik.lms.model.lecturer.CourseContentPost;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private Long id;
    @Column( unique=true, nullable=false )
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;

    private Set<CourseContentPost> courseContentPosts;



    @Version
    private Integer version;


    private ArrayList<String> theRoles;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<CourseContentPost> getCourseContentPosts(){
        return courseContentPosts;
    }

    public void setCourseContentPosts(Set<CourseContentPost> courseContentPosts){
        this.courseContentPosts = courseContentPosts;
    }

    @Transient
    public ArrayList<String> getTheRoles() {
        return theRoles;
    }

    public void setTheRoles(ArrayList<String> theRoles) {
        this.theRoles = theRoles;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
