package com.viachaslausviatski.pet_project.repositories;

import com.viachaslausviatski.pet_project.entity.MyObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyObjectRepository extends JpaRepository<MyObject,Long> {

    List<MyObject> findByName(String name);
}
