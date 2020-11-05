package com.appweb.university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appweb.university.entity.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long> {

}
