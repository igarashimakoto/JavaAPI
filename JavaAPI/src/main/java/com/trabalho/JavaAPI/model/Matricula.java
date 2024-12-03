package com.trabalho.JavaAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId("alunoId")
    @JsonBackReference
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    @ManyToOne
    @MapsId("turmaId")
    @JsonBackReference
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    private Turma turma;

    public Integer getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
