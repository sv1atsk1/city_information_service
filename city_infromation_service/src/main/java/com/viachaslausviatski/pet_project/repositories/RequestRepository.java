package com.viachaslausviatski.pet_project.repositories;

import com.viachaslausviatski.pet_project.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> {
}
