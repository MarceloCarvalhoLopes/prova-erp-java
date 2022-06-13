CREATE TABLE PRODUTO (
	id BIGSERIAL CONSTRAINT pk_id_produto PRIMARY KEY,
	nome VARCHAR(30) NOT NULL,
	descricao VARCHAR(100),
	preco NUMERIC,
	qtde_estoque INTEGER,
	tipo VARCHAR(20),
	situacao BOOLEAN NOT NULL	
);

INSERT INTO PRODUTO(nome,descricao,preco,qtde_estoque,tipo,situacao) VALUES ('Notebook','Última geração',5000.00,5,'PRODUTO', TRUE);
INSERT INTO PRODUTO(nome,descricao,preco,qtde_estoque,tipo,situacao) VALUES ('Café','Extra forte',200.00,100,'PRODUTO',TRUE);
INSERT INTO PRODUTO(nome,descricao,preco,qtde_estoque,tipo,situacao) VALUES ('Limpeza Notebook','limpeza das boas',100.00,1,'SERVICO',TRUE);
INSERT INTO PRODUTO(nome,descricao,preco,qtde_estoque,tipo,situacao) VALUES ('Entrega','mais rápida possível',500.00,1,'SERVICO',TRUE);