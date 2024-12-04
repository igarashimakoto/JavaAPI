package com.trabalho.JavaAPI.dto;

import java.util.Date;

public record NotaRequestDTO(Integer matricula_id, Integer disciplina_id, Float nota, Date data_lancamento) {
}
