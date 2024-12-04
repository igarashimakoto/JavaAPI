package com.trabalho.JavaAPI.repository;

import com.trabalho.JavaAPI.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    List<Turma> findByCursoId(Integer cursoId);


    List<Turma> findBySemestre(Integer semestre);


    @Query("SELECT t FROM Turma t WHERE t.curso.id = :cursoId AND t.semestre = :semestre")
    List<Turma> findByCursoIdAndSemestre(Integer cursoId, Integer semestre);
}
