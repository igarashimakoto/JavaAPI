create table turmas (

    id int not null primary key auto_increment,
    ano int,
    semestre INT CHECK (semestre IN (1, 2)),
    curso_id int not null,
    CONSTRAINT fk_cursoTurm FOREIGN KEY (curso_id) REFERENCES cursos(id)
)