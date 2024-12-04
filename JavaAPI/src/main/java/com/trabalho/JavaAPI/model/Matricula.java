package com.trabalho.JavaAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    @JsonIgnoreProperties("matriculas")
    private Aluno aluno;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    @JsonIgnoreProperties("matriculas")
    private Turma turma;

    @OneToMany(mappedBy = "nota")
    @JsonIgnoreProperties("nota")
    private List<Nota> notas;

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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
