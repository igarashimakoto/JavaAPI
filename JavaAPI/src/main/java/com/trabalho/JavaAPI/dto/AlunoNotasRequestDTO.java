package com.trabalho.JavaAPI.dto;

import java.util.Date;

public record AlunoNotasRequestDTO(Integer disciplinaId, Integer matriculaId, Float nota) {
}
