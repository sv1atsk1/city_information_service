package viachaslausviatski.city_infromation_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="MyObject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_name", nullable = false)
    private String objectName;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ObjectType objectType;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "opening_date", nullable = false)
    private java.util.Date openingDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "object")
    private List<Event> events;
}
