package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.ProfessorRequestDTO;
import com.trabalho.JavaAPI.model.Professor;
import com.trabalho.JavaAPI.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")

public class ProfessorControler {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {

        Professor professor = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody ProfessorRequestDTO dto) {
        Professor professor = new Professor();

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        Professor savedProfessor = this.repository.save(professor);

        return ResponseEntity.status(201).body(savedProfessor);
    }

    @PutMapping("/{id}")
    public  Professor update(@PathVariable Integer id, @RequestBody ProfessorRequestDTO dto) {
        Professor professor = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Professor professor = this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        this.repository.delete(professor);
        return ResponseEntity.noContent().build();
}


}
