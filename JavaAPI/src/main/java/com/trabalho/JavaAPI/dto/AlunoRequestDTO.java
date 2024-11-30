package com.trabalho.JavaAPI.dto;

import java.util.Date;

public record AlunoRequestDTO(String nome, String email, String matricula, Date dataNascimento) {
}
