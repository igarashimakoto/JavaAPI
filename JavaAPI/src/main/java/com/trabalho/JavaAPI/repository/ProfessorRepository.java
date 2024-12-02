package com.trabalho.JavaAPI.repository;

import com.trabalho.JavaAPI.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
