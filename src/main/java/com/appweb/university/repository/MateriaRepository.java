package com.appweb.university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appweb.university.entity.Materia;
@Repository
public interface MateriaRepository extends CrudRepository<Materia, Long> {

}
