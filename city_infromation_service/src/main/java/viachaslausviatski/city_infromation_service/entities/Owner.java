package viachaslausviatski.city_infromation_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Owner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_type", nullable = false)
    private String ownerType;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;
}
