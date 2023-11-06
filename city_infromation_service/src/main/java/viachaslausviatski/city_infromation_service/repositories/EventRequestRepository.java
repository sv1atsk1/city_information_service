package viachaslausviatski.city_infromation_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import viachaslausviatski.city_infromation_service.entities.EventRequest;

public interface EventRequestRepository extends JpaRepository<EventRequest,Integer> {
}