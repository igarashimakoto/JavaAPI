package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.MatriculaRequestDTO;
import com.trabalho.JavaAPI.model.Aluno;
import com.trabalho.JavaAPI.model.Matricula;
import com.trabalho.JavaAPI.model.Turma;
import com.trabalho.JavaAPI.repository.AlunoRepository;
import com.trabalho.JavaAPI.repository.MatriculaRepository;
import com.trabalho.JavaAPI.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")

public class MatriculaControler {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;


    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {

        Matricula matricula = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();

        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        matricula.setAluno(aluno);

        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        matricula.setTurma(turma);

        Matricula savedMatricula = this.repository.save(matricula);

        return ResponseEntity.status(201).body(savedMatricula);
    }

    @PutMapping("/{id}")
    public  Matricula update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        matricula.setAluno(aluno);

        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        matricula.setTurma(turma);

        return this.repository.save(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

    Matricula matricula = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        this.repository.delete(matricula);
        return ResponseEntity.noContent().build();
    }

}
