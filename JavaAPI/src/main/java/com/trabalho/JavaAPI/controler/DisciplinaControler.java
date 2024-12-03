package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.DisciplinaRequestDTO;
import com.trabalho.JavaAPI.model.Curso;
import com.trabalho.JavaAPI.model.Disciplina;
import com.trabalho.JavaAPI.model.Professor;
import com.trabalho.JavaAPI.repository.CursoRepository;
import com.trabalho.JavaAPI.repository.DisciplinaRepository;
import com.trabalho.JavaAPI.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")

public class DisciplinaControler {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;


    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {

        Disciplina disciplina = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado"));
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = this.repository.save(disciplina);

        return ResponseEntity.status(201).body(savedDisciplina);
    }

    @PutMapping("/{id}")
    public  Disciplina update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Disciplina disciplina = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
}


}
