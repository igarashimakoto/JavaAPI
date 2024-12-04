package com.trabalho.JavaAPI.controler;

import com.trabalho.JavaAPI.dto.AlunoNotasAlterRequestDTO;
import com.trabalho.JavaAPI.dto.AlunoNotasRequestDTO;
import com.trabalho.JavaAPI.dto.AlunoRequestDTO;
import com.trabalho.JavaAPI.model.Aluno;
import com.trabalho.JavaAPI.model.Disciplina;
import com.trabalho.JavaAPI.model.Matricula;
import com.trabalho.JavaAPI.model.Nota;
import com.trabalho.JavaAPI.repository.AlunoRepository;
import com.trabalho.JavaAPI.repository.DisciplinaRepository;
import com.trabalho.JavaAPI.repository.MatriculaRepository;
import com.trabalho.JavaAPI.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")

public class AlunoControler {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {

        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/{alunoId}/notas")
    public ResponseEntity<List<Nota>> buscarNotasDoAluno(@PathVariable Integer alunoId) {
        // Validar se o aluno existe
        repository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        List<Nota> notas = notaRepository.findAllByAlunoId(alunoId);

        return ResponseEntity.ok(notas);
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

    @PostMapping("/{alunoId}/notas")
    public ResponseEntity<Nota> adicionarNota(
            @PathVariable Integer alunoId, @RequestBody AlunoNotasRequestDTO dto) {

        Aluno aluno = repository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        Matricula matricula = matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        if (!matricula.getAluno().equals(aluno)) {
            throw new IllegalArgumentException("Matrícula não pertence ao aluno informado");
        }

        Nota novaNota = new Nota();
        novaNota.setMatricula(matricula);
        novaNota.setDisciplina(disciplina);
        novaNota.setNota(dto.nota());
        novaNota.setData_lancamento(new Date());

        Nota notaSalva = notaRepository.save(novaNota);

        return ResponseEntity.status(201).body(notaSalva);
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

    @PutMapping("/{alunoId}/notas/{notaId}")
    public ResponseEntity<Nota> alterarNota(
            @PathVariable Integer alunoId, @RequestBody AlunoNotasAlterRequestDTO dto) {

        // Validar nota existente
        Nota nota = notaRepository.findById(dto.notaId())
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        if (!nota.getMatricula().getAluno().getId().equals(alunoId)) {
            throw new IllegalArgumentException("Nota não pertence ao aluno informado");
        }

        // Atualizar nota
        nota.setNota(dto.novaNota());
        nota.setData_lancamento(new Date());
        Nota notaAtualizada = notaRepository.save(nota);

        return ResponseEntity.ok(notaAtualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {

        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{alunoId}/notas/{notaId}")
    public ResponseEntity<Void> excluirNota(@PathVariable Integer alunoId, @PathVariable Integer notaId) {

        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        if (!nota.getMatricula().getAluno().getId().equals(alunoId)) {
            throw new IllegalArgumentException("Nota não pertence ao aluno informado");
        }

        notaRepository.delete(nota);

        return ResponseEntity.noContent().build();
    }


}
