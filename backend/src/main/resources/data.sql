INSERT INTO generos(id, nome) VALUES (1, 'MASCULINO')
    ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;

INSERT INTO generos(id, nome) VALUES (2, 'FEMININO')
    ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;

INSERT INTO generos(id, nome) VALUES (3, 'OUTRO')
    ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;

INSERT INTO generos(id, nome) VALUES (4, 'NÃO ESPECIFICADO')
    ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;

INSERT INTO roles(id, nome) VALUES (1, 'CLIENTE')
ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;

INSERT INTO roles(id, nome) VALUES (2, 'ADMIN')
    ON CONFLICT (id) DO UPDATE SET nome = EXCLUDED.nome;



