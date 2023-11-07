package viachaslausviatski.city_infromation_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import viachaslausviatski.city_infromation_service.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
