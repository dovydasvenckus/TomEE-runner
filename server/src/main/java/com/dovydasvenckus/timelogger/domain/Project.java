package com.dovydasvenckus.timelogger.domain;

import javax.persistence.*;
import javax.persistence.Column;

@Entity
@Table( name = "books")
@NamedQuery(name = "findAll", query = "SELECT p FROM Project")
public class Project {
    
    @Id
    @GeneratedValue
    Long id;
    
    @Column(nullable = false)
    String name;

    String description;

    public Project(){}

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

