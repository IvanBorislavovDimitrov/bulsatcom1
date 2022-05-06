DROP DATABASE IF EXISTS bulsatcom;
CREATE DATABASE bulsatcom;
USE bulsatcom;

CREATE TABLE providers(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL
);

CREATE TABLE contract_provider(
id INT AUTO_INCREMENT PRIMARY KEY,
provider_id INT NOT NULL,
CONSTRAINT FOREIGN KEY (provider_id)
REFERENCES providers(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
price DECIMAL(7,2) NOT NULL,
firstDate DATE NOT NULL,
lastDate DATE NOT NULL
);

CREATE TABLE channels(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
contract_provider INT NOT NULL,
CONSTRAINT FOREIGN KEY (contract_provider)
REFERENCES contract_provider(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
category VARCHAR(255) NOT NULL,
price DECIMAL(7,2) NOT NULL
);

CREATE TABLE packages(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL
);

CREATE TABLE package_channel(
package_id INT NOT NULL,
CONSTRAINT FOREIGN KEY (package_id)
REFERENCES packages(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
channel_id INT NOT NULL,
CONSTRAINT FOREIGN KEY (channel_id)
REFERENCES channels(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);


CREATE TABLE user(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
username VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
address VARCHAR(255) NOT NULL,
telephone VARCHAR(255) NOT NULL,
egn VARCHAR(10) NOT NULL UNIQUE
);




INSERT INTO providers
VALUES 
	(NULL, 'NOVA Broadcasting Group'),
    (NULL, 'WarnerMedia');

INSERT INTO contract_provider
VALUES	(NULL, 1, 13550, '2022-04-07', '2024-04-07'),
		(NULL, 2, 21000, '2022-04-08', '2024-04-08');

INSERT INTO channels(name, contract_provider, category, price)
VALUES	('Diema', 1, 'cinema', 0.50),
        ('Diema Family', 1, 'entertainment', 0.50),
        ('Diema Sport HD', 1, 'sport', 9.99),
		('Diema Sport 2 HD', 1, 'sport', 9.99),
        ('Diema Sport 3 HD', 1, 'sport', 9.99),
        ('Trace Sport Stars HD', 1, 'sport', 9.99),
        ('The Voce', 1, 'music', 0.6),
        ('Magic TV', 1, 'music', 0.45),
        ('HBO', 2, 'cinema', 9.99),
        ('HBO 2', 2, 'cinema', 9.99),
        ('HBO 3', 2, 'cinema', 9.99),
		('CINEMAX', 2, 'cinema', 1.99),
        ('CINEMAX 2', 2, 'cinema', 1.99),
        ('NOVA', 1, 'entertainment', 1.35),
		('Kino Nova', 1, 'cinema', 0.80),
		('Diema Sport', 1, 'sport', 0.85),
        ('NOVA Sport', 1, 'sport', 0.75);
        
INSERT INTO packages
VALUES 	(NULL, 'HBO'),
		(NULL, 'DIEMAXTRA'),
        (NULL, 'HBO+CINEMAX'),
        (NULL, 'CINEMAX');
        
INSERT INTO package_channel
VALUES 	(1,9), (1,10), (1,11),
		(2,1),
		(2,2),
        (2,3),
        (2,4),
        (2,5),
        (3,9),
        (3,10),
        (3,11),
        (3,12),
        (3,13),
        (4,12),
        (4,13);
        
INSERT INTO customers(name, egn, telephone, address)
VALUES ('Ivan Georgiev Petrov', '0011223344', '0881234567', 'Sofia, zh.k. Liulyin - bl.6');