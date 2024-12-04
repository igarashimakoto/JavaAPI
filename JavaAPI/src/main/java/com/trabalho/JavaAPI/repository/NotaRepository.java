package com.trabalho.JavaAPI.repository;

import com.trabalho.JavaAPI.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    @Query("SELECT n FROM Nota n WHERE n.matricula.aluno.id = :alunoId")
    List<Nota> findAllByAlunoId(@Param("alunoId") Integer alunoId);

    @Query("SELECT n FROM Nota n WHERE n.matricula.turma.id = :turmaId")
    List<Nota> findByTurmaId(@Param("turmaId") Integer turmaId);

    @Query("SELECT n FROM Nota n WHERE n.disciplina.id = :disciplinaId")
    List<Nota> findByDisciplinaId(@Param("disciplinaId") Integer disciplinaId);


}
