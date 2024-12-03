package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.CursoRequestDTO;
import com.trabalho.JavaAPI.model.Curso;
import com.trabalho.JavaAPI.model.Disciplina;
import com.trabalho.JavaAPI.repository.CursoRepository;
import com.trabalho.JavaAPI.repository.DisciplinaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")

public class CursoControler {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id) {

        Curso curso = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("curso não encontrado"));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        Curso savedCurso = this.repository.save(curso);

        return ResponseEntity.status(201).body(savedCurso);
    }

    @PutMapping("/{id}")
    public  Curso update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("curso não encontrado"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @PutMapping("/add-disciplina/{id}")
    public ResponseEntity<Curso> addDisciplina(@PathVariable Integer id, @RequestBody Disciplina disciplina) {

        Curso curso = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("curso não encontrado"));

        disciplina.setCurso(curso);
        this.disciplinaRepository.save(disciplina);

        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Curso curso = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("curso não encontrado"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }


}
