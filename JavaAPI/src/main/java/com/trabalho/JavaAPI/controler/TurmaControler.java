package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.TurmaRequestDTO;
import com.trabalho.JavaAPI.model.Curso;
import com.trabalho.JavaAPI.model.Turma;
import com.trabalho.JavaAPI.repository.CursoRepository;
import com.trabalho.JavaAPI.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")

public class TurmaControler {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public ResponseEntity<List<Turma>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {

        Turma turma = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));
        return ResponseEntity.ok(turma);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Turma>> findByCurso(@PathVariable Integer cursoId) {
        List<Turma> turmas = this.repository.findByCursoId(cursoId);
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/semestre/{semestre}")
    public ResponseEntity<List<Turma>> findBySemestre(@PathVariable Integer semestre) {
        List<Turma> turmas = this.repository.findBySemestre(semestre);
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/curso/{cursoId}/semestre/{semestre}")
    public ResponseEntity<List<Turma>> findByCursoAndSemestre(
            @PathVariable Integer cursoId,
            @PathVariable Integer semestre) {
        List<Turma> turmas = this.repository.findByCursoIdAndSemestre(cursoId, semestre);
        return ResponseEntity.ok(turmas);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        turma.setCurso(curso);

        Turma savedTurma = this.repository.save(turma);

        return ResponseEntity.status(201).body(savedTurma);
    }

    @PutMapping("/{id}")
    public  Turma update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Turma turma = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
