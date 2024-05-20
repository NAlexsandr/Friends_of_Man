USE Friends;

CREATE TABLE pets (                                           /*Создаем таблицы из схемы "Домашние животные"*/
id INT AUTO_INCREMENT PRIMARY KEY,
name ENUM ('dogs', 'cats', 'hamsters')
 );
 CREATE TABLE pack_animals (
id INT AUTO_INCREMENT PRIMARY KEY,
name ENUM ('horses', 'camels', 'donkeys')
 );
 CREATE TABLE dogs
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    DogsId INT,
    name VARCHAR (50),
    вirthday DATE,
    instructions SET ('sit', 'lie', 'fas'),
    FOREIGN KEY (DogsId) REFERENCES pets (id) ON DELETE CASCADE
);
 CREATE TABLE cats
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    CatsId INT,
    name VARCHAR (50),
    вirthday DATE,
    instructions SET ('sit', 'lie', 'eat'),
    FOREIGN KEY (CatsId) REFERENCES pets (id) ON DELETE CASCADE
);
 CREATE TABLE hamsters
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    HamstersId INT,
    name VARCHAR (50),
	вirthday DATE,
    instructions SET ('lie', 'eat'),
    FOREIGN KEY (HamstersId) REFERENCES pets (id) ON DELETE CASCADE
);
 CREATE TABLE horses
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    HorsesId INT,
    name VARCHAR (50),
    вirthday DATE,
    instructions SET ('step', 'trot', 'galloping'),
    FOREIGN KEY (HorsesId) REFERENCES pack_animals (id) ON DELETE CASCADE
);
CREATE TABLE camels
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    CamelsId INT,
    name VARCHAR (50),
	вirthday DATE,
    instructions SET ('step', 'trot', 'galloping'),
    FOREIGN KEY (CamelsId) REFERENCES pack_animals (id) ON DELETE CASCADE
);
CREATE TABLE donkeys
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    DonkeysId INT,
    name VARCHAR (50),
    вirthday DATE,
    instructions SET ('step', 'trot'),
    FOREIGN KEY (DonkeysId) REFERENCES pack_animals (id) ON DELETE CASCADE
);

INSERT INTO pets (id, name)
VALUES (1, 'dogs'),
       (2, 'cats'),
       (3, 'hamsters'),
       (4, 'hamsters'),
	   (5, 'dogs'),
       (6, 'cats');

INSERT INTO dogs (DogsId, name, вirthday, instructions)
VALUES (1, 'BOB', '22-11-12', ('sit,lie')),
       (5, 'LESLI', '24-01-01', ('sit,lie,fas'));
INSERT INTO cats (CatsId, name, вirthday, instructions)
VALUES (2, 'BARSIK', '20-07-08', ('sit,eat')),
       (6, 'TIMOSHA', '19-05-03', ('sit,lie,eat'));
INSERT INTO hamsters (HamstersId, name, вirthday, instructions)
VALUES (3, 'MUDA', '20-07-08', ('eat')),
       (4, 'TRUDA', '19-05-03', ('lie,eat'));
       
INSERT INTO pack_animals (id, name)
VALUES (1, 'camels'),
       (2, 'horses'),
       (3, 'donkeys'),
       (4, 'horses'),
	   (5, 'donkeys'),
       (6, 'camels');

INSERT INTO horses (HorsesId, name, вirthday, instructions)
VALUES (2, 'APPOLON', '15-05-21', ('step,trot')),
       (4, 'VIKONT', '12-02-10', ('step,trot,galloping'));  
INSERT INTO camels (CamelsId, name, вirthday, instructions)
VALUES (1, 'PLEVOK', '16-10-22', ('step,trot,galloping')),
       (6, 'GORBUN', '17-03-11', ('step,trot'));
INSERT INTO donkeys (DonkeysId, name, вirthday, instructions)
VALUES (3, 'IA-IA', '21-09-02', ('step,trot')),
       (5, 'E-MOE', '17-08-10', ('step,trot'));
       
SELECT * FROM pets;
SELECT * FROM dogs;
SELECT * FROM cats;
SELECT * FROM hamsters;

SELECT * FROM pack_animals;
SELECT * FROM horses;
SELECT * FROM camels;
SELECT * FROM donkeys;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM pack_animals WHERE name = 'camels';            /* Удаление верблюдов, они уехали*/
SET SQL_SAFE_UPDATES = 1;

SELECT Id, name, вirthday, instructions FROM horses        /* Объединение лошадей и ослов в одну таблицу*/
UNION SELECT Id, name, вirthday, instructions
FROM donkeys;       

CREATE TABLE young_animals                                 /* Создаем таблицу "Молодые животные" */
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (50),
	вirthday DATE
);

INSERT INTO young_animals                                 /* Заполняем таблицу "Молодые животные" */ 
   (name, вirthday)  
SELECT name, вirthday 
FROM dogs;
INSERT INTO young_animals   
   (name, вirthday)  
SELECT name, вirthday 
FROM cats;
INSERT INTO young_animals   
   (name, вirthday)  
SELECT name, вirthday 
FROM hamsters;
INSERT INTO young_animals   
   (name, вirthday)  
SELECT name, вirthday 
FROM horses;
INSERT INTO young_animals   
   (name, вirthday)  
SELECT name, вirthday 
FROM camels;
INSERT INTO young_animals   
   (name, вirthday)  
SELECT name, вirthday 
FROM donkeys;

SELECT * FROM young_animals  ;
ALTER TABLE young_animals ADD COLUMN years INT;
ALTER TABLE young_animals ADD COLUMN months INT;

SET SQL_SAFE_UPDATES = 0;
UPDATE young_animals SET years = YEAR(CURRENT_DATE) - YEAR(вirthday), months = MONTH(CURRENT_DATE) - MONTH(вirthday); /*             ??? */
UPDATE young_animals SET years = IF(months > 0, years, years - 1), months = IF(months < 0, 12 - (-months), months);
DELETE FROM young_animals WHERE years < 1 OR years > 3;


SELECT pets.id, pets.name FROM pets
UNION
SELECT pack_animals.id, pack_animals.name FROM pack_animals;

CREATE TABLE pets_pack (                       /*Объединить все таблицы водну, при этом сохраняя поля, указывающиена прошлую принадлежность к старым таблицам.*/
id INT,
name VARCHAR(50)
 );

(SELECT pets.id, pets.name FROM pets
UNION
SELECT pack_animals.id, pack_animals.name FROM pack_animals);  

INSERT INTO pets_pack                           /*Через 2-ва INSERT INTO мы реализуем UNION */     
   (id, name)  
SELECT id, name 
FROM pets;

INSERT INTO pets_pack                                
   (id, name)  
SELECT id, name 
FROM pack_animals;
 
SELECT * FROM pets_pack;
                                              /*Через JOIN присоединяем другие таблицы */      
SELECT pets_pack.id, pets_pack.name, dogs.Id, dogs.DogsId, dogs.name, dogs.вirthday, dogs.instructions,
                           cats.Id, cats.CatsId, cats.name, cats.вirthday, cats.instructions,
                           hamsters.Id, hamsters.HamstersId, hamsters.name, hamsters.вirthday, hamsters.instructions,
                           horses.Id, horses.HorsesId, horses.name, horses.вirthday, horses.instructions,
                           camels.Id, camels.CamelsId, camels.name, camels.вirthday, camels.instructions,
                           donkeys.Id, donkeys.DonkeysId, donkeys.name, donkeys.вirthday, donkeys.instructions
FROM pets_pack
LEFT JOIN dogs ON pets_pack.id = dogs.DogsId AND pets_pack.name = 'dogs'
LEFT JOIN cats ON pets_pack.id = cats.CatsId AND pets_pack.name = 'cats'
LEFT JOIN hamsters ON pets_pack.id = hamsters.HamstersId AND pets_pack.name = 'hamsters'
LEFT JOIN horses ON pets_pack.id = horses.HorsesId AND pets_pack.name = 'horses'
LEFT JOIN camels ON pets_pack.id = camels.CamelsId AND pets_pack.name = 'camels'
LEFT JOIN donkeys ON pets_pack.id = donkeys.DonkeysId AND pets_pack.name = 'donkeys';
