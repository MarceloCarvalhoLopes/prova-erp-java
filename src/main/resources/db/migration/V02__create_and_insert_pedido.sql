CREATE TABLE PEDIDO (
	id BIGSERIAL CONSTRAINT pk_id_pedido PRIMARY KEY,
	data_pedido DATE,
	desconto NUMERIC,
	situacao BOOLEAN NOT NULL 
);

INSERT INTO PEDIDO(data_pedido,desconto,situacao)VALUES('2022-06-12',0.00,TRUE);
INSERT INTO PEDIDO(data_pedido,desconto,situacao)VALUES('2022-06-10',0.00,TRUE);

CREATE TABLE ITEM_PEDIDO (
	pedido_id INTEGER,
	produto_id INTEGER,
	quantidade INTEGER,
	preco NUMERIC,
	CONSTRAINT pk_id_item_pedido PRIMARY KEY(pedido_id,produto_id)	
);

INSERT INTO ITEM_PEDIDO(pedido_id,produto_id,quantidade,preco) VALUES (1,1,2,9500.00);
INSERT INTO ITEM_PEDIDO(pedido_id,produto_id,quantidade,preco) VALUES (1,3,1,100.00);
INSERT INTO ITEM_PEDIDO(pedido_id,produto_id,quantidade,preco) VALUES(2,2,2,50.00);