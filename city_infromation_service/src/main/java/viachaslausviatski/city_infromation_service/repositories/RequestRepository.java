package viachaslausviatski.city_infromation_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import viachaslausviatski.city_infromation_service.entities.Request;

public interface RequestRepository extends JpaRepository<Request,Integer> {
}
