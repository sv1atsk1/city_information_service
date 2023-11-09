package com.viachaslausviatski.pet_project.repositories;

import com.viachaslausviatski.pet_project.entity.MyObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyObjectRepository extends JpaRepository<MyObject,Long> {
}
