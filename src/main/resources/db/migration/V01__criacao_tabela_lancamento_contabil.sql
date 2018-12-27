CREATE TABLE lancamento_contabil (
	id serial PRIMARY KEY,
	conta_contabil char(7) NOT NULL,
	data date NOT NULL,
	valor numeric NOT NULL
);