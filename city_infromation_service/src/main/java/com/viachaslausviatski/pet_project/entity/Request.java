package com.viachaslausviatski.pet_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(name = "name_of_object")
    private String objectName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "name_of_event")
    private String eventName;

    @Column(name = "type_of_event")
    private String eventType;

    @Column(name = "full_name_of_user")
    private String userFullName;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToMany
    @JoinTable(
            name = "eventObject",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id")
    )
    private Set<MyObject> objects = new HashSet<>();

}
