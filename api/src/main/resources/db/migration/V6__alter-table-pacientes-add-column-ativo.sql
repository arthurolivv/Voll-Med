ALTER TABLE pacientes ADD COLUMN ativo BOOLEAN DEFAULT true;

UPDATE pacientes SET ativo = true WHERE ativo IS NULL;

ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;
