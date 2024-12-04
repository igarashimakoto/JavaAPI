create table notas (

    id int not null primary key auto_increment,
    matricula_id int,
    disciplina_id int,
    nota decimal(5, 2),
    data_lancamento date,
    CONSTRAINT fk_matriculaid FOREIGN KEY (matricula_id) REFERENCES matriculas(id),
    CONSTRAINT fk_disciplinaid FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id)
)