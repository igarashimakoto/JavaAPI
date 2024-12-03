create table matriculas (

    id int not null primary key auto_increment,
    aluno_id int,
    turma_id int,
    CONSTRAINT fk_alunoid FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    CONSTRAINT fk_turmaid FOREIGN KEY (turma_id) REFERENCES turmas(id)

)