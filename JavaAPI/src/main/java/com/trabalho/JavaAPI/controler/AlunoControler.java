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
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
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
}
