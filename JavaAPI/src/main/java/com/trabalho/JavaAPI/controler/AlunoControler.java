package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.AlunoRequestDTO;
import com.trabalho.JavaAPI.model.Aluno;
import com.trabalho.JavaAPI.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")

public class AlunoControler {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {

        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.dataNascimento());

        Aluno savedAluno = this.repository.save(aluno);

        return ResponseEntity.status(201).body(savedAluno);
    }

    @PutMapping("/{id}")
    public  Aluno update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.dataNascimento());

        return this.repository.save(aluno);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
}


}
