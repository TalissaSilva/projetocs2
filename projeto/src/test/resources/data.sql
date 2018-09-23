

INSERT INTO endereco VALUES ('79011-000', 'coronel antonino', 'Campo Grande', 'Av. Coronel Antonino', 'ms');
INSERT INTO endereco VALUES ('79011-290', 'Cel. Antonino', 'Campo Grande', 'R. Santo Ângelo', 'ms');
INSERT INTO endereco VALUES ('79022-060', 'Vila Rica', 'Campo Grande', 'R. Santa Bárbara', 'ms');
INSERT INTO endereco VALUES ('79118-240', 'São francisco', 'Campo Grande', 'Rua Juiz de Fora', 'ms');

INSERT INTO foto VALUES (7, 'MTIzMTM1MzJfODgwMzA2NDc1Mzk5NzI5XzQ4NDkxMDEzNjM2MTU3Nzk4MjJfbi5qcGcxNTM2NDI2Nzg3OTYz.jpg', NULL);
INSERT INTO usuario VALUES (1, 'matheuscg10@live.com', 'matheus ale da silva', 2091, '123456', '(67) 9 9247-5858', '(67) 9924-7585', '79011-000', 7);

INSERT INTO pessoa_fisica VALUES ('333.333.333-33', 1);

INSERT INTO imovel VALUES (1, 30, 50, 2091, 0, 1, '79011-000');
INSERT INTO imovel VALUES (2, 20, 20, 51, 1, 1, '79011-290');
INSERT INTO imovel VALUES (3, 10, 10, 629, 1, 1, '79022-060');

INSERT INTO foto VALUES (1, 'MTUzNjQyMDMzMzcxMQ==.jpeg', 1);
INSERT INTO foto VALUES (2, 'MTUzNjQyMDMzMzc5Mw==.jpeg', 1);
INSERT INTO foto VALUES (3, 'MTUzNjQyMDMzMzc5NQ==.jpeg', 1);
INSERT INTO foto VALUES (4, 'MTUzNjQyMTM0NTY4MQ==.jpeg', 2);
INSERT INTO foto VALUES (5, 'MTUzNjQyMTM0NTY4NA==.jpeg', 2);
INSERT INTO foto VALUES (6, 'MTUzNjQyMTQ2OTIxOQ==.jpeg', 3);
INSERT INTO foto VALUES (8, 'MTUzNjgwMjI2NDYwNg==.jpeg', NULL);

INSERT INTO imovel_caracteristicas VALUES (1, 'Banheiro, 2');
INSERT INTO imovel_caracteristicas VALUES (1, 'Sacada, 2');
INSERT INTO imovel_caracteristicas VALUES (1, 'Cozinha, 2');
INSERT INTO imovel_caracteristicas VALUES (2, 'Quarto, 2');
INSERT INTO imovel_caracteristicas VALUES (2, 'Sacada, 1');
INSERT INTO imovel_caracteristicas VALUES (2, 'Banheiro, 2');
INSERT INTO imovel_caracteristicas VALUES (3, 'Banheiro, 1');
INSERT INTO imovel_caracteristicas VALUES (3, 'Quarto, 1');
INSERT INTO imovel_caracteristicas VALUES (3, 'Cozinha, 1');
INSERT INTO imovel_caracteristicas VALUES (3, 'Sacada, 1');

INSERT INTO anuncio VALUES (1, '2018-09-08', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque a rutrum mauris. Suspendisse potenti. Duis iaculis fermentum dapibus. Pellentesque diam nunc, mattis in massa ut, blandit laoreet metus. Phasellus risus arcu, imperdiet eu molestie vel, congue eget mauris. Ut euismod lorem rhoncus condimentum rhoncus. Praesent rhoncus faucibus lectus id gravida. Cras sit amet elit ac libero faucibus semper vel ac ex. Sed ut mauris cursus erat interdum ultricies. Sed gravida turpis non libero tempor congue. Ut vulputate fermentum nisl ac vestibulum. Pellentesque sit amet tellus efficitur, volutpat lorem quis, ultrices ex. Suspendisse quis justo eu eros tempor maximus. Donec vitae eleifend sem. Phasellus non facilisis lectus. Quisque dignissim ultricies turpis, nec vestibulum ipsum laoreet at. Donec vel gravida quam, eget ornare tellus. Proin id arcu tristique, volutpat elit et, venenatis felis. Phasellus elementum libero consectetur velit convallis pulvinar.', 1, 'Casa', 1500000, 1, 1);
INSERT INTO anuncio VALUES (2, '2018-09-08', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque a rutrum mauris. Suspendisse potenti. Duis iaculis fermentum dapibus. Pellentesque diam nunc, mattis in massa ut, blandit laoreet metus. Phasellus risus arcu, imperdiet eu molestie vel, congue eget mauris. Ut euismod lorem rhoncus condimentum rhoncus. Praesent rhoncus faucibus lectus id gravida.', 0, 'Apartamento para solteiro', 800, 1, 3);
INSERT INTO anuncio VALUES (3, '2018-09-23', 'Descricao 1', 0, 'Titulo 1', 1, 1, 3);
INSERT INTO anuncio VALUES (4, '2018-09-23', 'descrição 2', 1, 'anuncio 2', 2, 1, 2);

