package com.turkcell.data.repository;

import com.turkcell.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    //delivered Query
    //List<EmployeeEntity> findEmployeeEntitiesByEmployeeNameStartsWith(String name);
}
