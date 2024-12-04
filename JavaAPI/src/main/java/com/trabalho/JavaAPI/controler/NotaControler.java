package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.DisciplinaRequestDTO;
import com.trabalho.JavaAPI.dto.NotaRequestDTO;
import com.trabalho.JavaAPI.model.*;
import com.trabalho.JavaAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")

public class NotaControler {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {

        Nota nota = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        return ResponseEntity.ok(nota);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Nota>> findByAlunoId(@PathVariable Integer alunoId) {
        List<Nota> notas = this.repository.findAllByAlunoId(alunoId);
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Nota>> findByTurmaId(@PathVariable Integer turmaId) {
        List<Nota> notas = this.repository.findByTurmaId(turmaId);
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Nota>> findByDisciplinaId(@PathVariable Integer disciplinaId) {
        List<Nota> notas = this.repository.findByDisciplinaId(disciplinaId);
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }


    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        nota.setMatricula(matricula);

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);

        Nota savedNota = this.repository.save(nota);

        return ResponseEntity.status(201).body(savedNota);
    }

    @PutMapping("/{id}")
    public  Nota update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {

        Nota nota = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        nota.setMatricula(matricula);

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);

        return this.repository.save(nota);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Nota nota = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        this.repository.delete(nota);
        return ResponseEntity.noContent().build();
}


}
