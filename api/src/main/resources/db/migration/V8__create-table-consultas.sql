CREATE TABLE consultas (

                            id BIGSERIAL PRIMARY KEY,
                            id_medico BIGINT NOT NULL,
                            id_paciente BIGINT NOT NULL,
                            data DATE NOT NULL,

                            CONSTRAINT fk_consulta_medico
                            foreign key (id_medico)
                            references medicos(id),

                            constraint fk_consulta_paciente
                            foreign key (id_paciente)
                            references pacientes(id)

);