package com.viachaslausviatski.pet_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "number_of_visitors")
    private int numberOfVisitors;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy="event")
    private List<Images> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreated;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private MyObject object;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToEvent(Images image) {
        image.setEvent(this);
        images.add(image);
    }



}
