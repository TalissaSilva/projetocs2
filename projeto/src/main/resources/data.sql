INSERT INTO atributo VALUES (1, 'Quantos'),
(2, 'Banheiros'),
(3, 'Caragem'),
(4, 'Cozinha'),
(5, 'Varanda'),
(6, 'Andares');

INSERT INTO endereco
(bairro, cidade, logradouro, numero, uf, cep) values
('bairro', 'cidade', 'logradouro', 1, 'ms', '70911000');

INSERT INTO foto VALUES (1, 'foo url');

INSERT INTO usuario
(id, email, endereco_cep, foto_perfil_id, nome, senha, telefone01, telefone02) values
(1, 'user@email', '70911000', 1, 'nome', '123456', '', '');

 INSERT INTO pessoa_fisica
 (cpf, id) values
 ('00000000000', 1);