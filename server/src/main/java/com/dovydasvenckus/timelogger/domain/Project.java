package com.dovydasvenckus.timelogger.domain;

import java.util.List;
import javax.persistence.*;
import javax.persistence.Column;

@Entity
@Table( name = "books")
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;

    private String description;
    
    @JoinTable( name = "project_tags",
            joinColumns = @JoinColumn( name = "project_id"),
            inverseJoinColumns = @JoinColumn( name = "tag_id")
    )
    @OrderBy( "name DESC")
    private List<Tag> tags;
    
    @OneToMany( orphanRemoval = true, 
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
                       CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn( name = "project_id")
    private List<TimeLogEntry> timeLogEntries;

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<TimeLogEntry> getTimeLogEntries() {
        return timeLogEntries;
    }

    public void setTimeLogEntries(List<TimeLogEntry> timeLogEntries) {
        this.timeLogEntries = timeLogEntries;
    }
}

