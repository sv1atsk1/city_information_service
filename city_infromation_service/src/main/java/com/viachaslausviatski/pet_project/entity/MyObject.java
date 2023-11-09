package com.viachaslausviatski.pet_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Objects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long id;

    @Column(name = "object_name")
    private String name;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private String type;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "work_status")
    private Date workStatus;

    @Column(name = "popularity")
    private Integer popularity;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy="object")
    private List<Images> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "object")
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "eventObject",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "request_id")
    )
    private Set<Request> requests = new HashSet<>();

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToObject(Images image) {
        image.setObject(this);
        images.add(image);
    }

}
