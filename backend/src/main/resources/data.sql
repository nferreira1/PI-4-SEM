INSERT INTO generos (nome)
SELECT 'MASCULINO'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'MASCULINO');

INSERT INTO generos (nome)
SELECT 'FEMININO'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'FEMININO');

INSERT INTO generos (nome)
SELECT 'OUTRO'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'OUTRO');

INSERT INTO generos (nome)
SELECT 'NÃO ESPECIFICADO'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'NÃO ESPECIFICADO');


DELIMITER //

CREATE PROCEDURE SPU_GERARPEDIDOS(
    IN ds_nome VARCHAR(30),
    IN ds_descricao VARCHAR(30),
    IN nr_id_pedido INT,
    IN nr_id_produto INT,
    IN nr_quantidade INT
)
BEGIN
    DECLARE nr_estoque_produto INT;
    DECLARE nr_estoque_atual INT;

INSERT INTO pedidos_itens(nome, descricao, quantidade, valor, produto_id, pedido_id)
VALUES(ds_nome, ds_descricao, nr_quantidade, nr_id_produto, nr_id_pedido);

SELECT estoque INTO nr_estoque_produto
FROM produtos
WHERE id = nr_id_produto;

SET nr_estoque_atual = nr_estoque_produto - nr_quantidade;

    IF nr_estoque_atual >= 0 THEN

UPDATE produtos
SET estoque = nr_estoque_atual
WHERE id = nr_id_produto;
ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Estoque insuficiente';
END IF;

SELECT * FROM pedidos_itens WHERE pedido_id = nr_id_pedido;
END //

DELIMITER ;

use treinandoAPI;