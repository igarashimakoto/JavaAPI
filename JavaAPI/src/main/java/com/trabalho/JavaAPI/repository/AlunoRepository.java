package com.trabalho.JavaAPI.repository;

import com.trabalho.JavaAPI.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
