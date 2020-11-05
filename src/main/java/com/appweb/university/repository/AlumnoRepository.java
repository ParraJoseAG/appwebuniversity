package com.appweb.university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appweb.university.entity.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, String> {

}
