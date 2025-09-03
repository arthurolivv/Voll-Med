UPDATE medicos SET telefone = 'sem telefone';

ALTER TABLE medicos ALTER COLUMN telefone SET NOT NULL;
