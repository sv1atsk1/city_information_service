package viachaslausviatski.city_infromation_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Popularity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Popularity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "object_id", nullable = false)
    private Object object;

    @Column(name = "popularity_date", nullable = false)
    private java.util.Date popularityDate;

    @Column(name = "number_of_visitors", nullable = false)
    private int numberOfVisitors;
}
