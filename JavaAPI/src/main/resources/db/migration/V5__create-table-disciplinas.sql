create table disciplinas (

    id int not null primary key auto_increment,
    nome varchar(100),
    codigo varchar(20),
    curso_id int not null,
    professor_id int not null,
    CONSTRAINT fk_cursoDisc FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_professorDisc FOREIGN KEY (professor_id) REFERENCES professores(id)

)