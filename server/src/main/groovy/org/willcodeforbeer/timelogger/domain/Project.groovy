package org.willcodeforbeer.timelogger.domain

import javax.persistence.*;
import javax.persistence.Column

@Entity
class Project {
    
    @Id @GeneratedValue
    Long id
    
    @Column(nullable = false)
    String name

    String description
}

