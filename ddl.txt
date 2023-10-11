CREATE TABLE
    pessoa (
        id INT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        cpf VARCHAR(11) NOT NULL,
        data_de_nascimento DATE,
        setor VARCHAR(50)
    );

CREATE TABLE
    ingresso (
        id INT NOT NULL AUTO_INCREMENT,
        dia_festival varchar(255) DEFAULT NULL,
        dt_registro datetime(6) DEFAULT NULL,
        setor varchar(255) DEFAULT NULL,
        pessoa_id INT DEFAULT NULL,
        PRIMARY KEY (id),
        CONSTRAINT fk_pessoa_ingresso FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
    );