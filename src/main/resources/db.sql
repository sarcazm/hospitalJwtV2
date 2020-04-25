CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   CHARACTER VARYING(30) NOT NULL,
    role      CHARACTER VARYING(50) default 'ROLE_USER',
    password   CHARACTER VARYING(250) NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS patients
(
    id         SERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(50) NOT NULL,
    last_name  CHARACTER VARYING(50) NOT NULL,
    phone      CHARACTER VARYING(50) NOT NULL,
    snils      CHARACTER VARYING(50) NOT NULL,
    username   CHARACTER VARYING(30) NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'
);


CREATE TABLE IF NOT EXISTS specialties
(
    id         SERIAL PRIMARY KEY,
    name       CHARACTER VARYING(50) NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS doctors
(
    id         SERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(50) NOT NULL,
    last_name  CHARACTER VARYING(50) NOT NULL,
    phone      CHARACTER VARYING(50) NOT NULL,
    specialty_id      INTEGER REFERENCES specialties (id),
    username   CHARACTER VARYING(30) NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS services
(
    id         SERIAL PRIMARY KEY,
    name       CHARACTER VARYING(50) NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS records
(
    id         SERIAL PRIMARY KEY,
    patient_id      INTEGER REFERENCES patients (id),
    doctor_id      INTEGER REFERENCES doctors (id),
    service_id      INTEGER REFERENCES services (id),
    date      DATE NOT NULL,
    status CHARACTER VARYING(25) default 'ACTIVE'

);



/*services*/
INSERT INTO services (name) VALUES ('platno'),
                                   ('besplatno');
/*specialties*/
INSERT INTO specialties (name) VALUES ('ginekolog'),
                                      ('urolog'),
                                      ('oftalmolog'),
                                      ('terapevt'),
                                      ('pediatr'),
                                      ('hirurg'),
                                      ('gastroenterolog');
/*users*/
INSERT INTO users (username, role, password) VALUES ('doc1', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc2', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc3', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc4', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc5', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc6', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('doc7', 'ROLE_ADMIN', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('pat1', 'ROLE_USER', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('pat2', 'ROLE_USER', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('pat3', 'ROLE_USER', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y'),
                                                    ('pat4', 'ROLE_USER', '$2a$04$N.ChmY7WTk157vuyy6n4CuJz8f0osCevAe8Uh8qEzZFpJDHMVEO/y');

/*doctors*/
INSERT INTO doctors (username, first_name, last_name, phone, specialty_id) VALUES ('doc1', 'Petr', 'Ivanov', '8-909-654-63-96', 6),
                                                                                                  ('doc2', 'Irina','Nazarova', '8-926-545-33-88', 5),
                                                                                                  ('doc3', 'Svetlana','Petrova', '8-926-545-33-88', 4),
                                                                                                  ('doc4', 'Olesya','Nikiforova', '8-917-885-18-17', 3),
                                                                                                  ('doc5', 'Oleg','Nikulin', '8-917-885-18-17', 2),
                                                                                                  ('doc6', 'Elena','Nikulina', '8-917-885-18-17', 1),
                                                                                                  ('doc7', 'Marina','Nerstova', '8-917-885-18-17', 7);

INSERT INTO patients (username, first_name, last_name, phone, snils) VALUES ('pat1', 'Petr', 'Ivanov', '8-909-654-63-96', '777-636-251-252'),
                                                                                            ('pat2', 'Andrey', 'Uskov', '8-968-114-85-36', '187-178-987-055'),
                                                                                            ('pat3', 'Vladimir', 'Morozov', '8-928-574-69-66', '887-957-505-412'),
                                                                                            ('pat4', 'Alexandr', 'Stroganov', '8-906-852-82-67', '237-155-915-112');
/*records*/
INSERT INTO records (patient_id, doctor_id, service_id, date) VALUES (1,2,2,'25.07.2020'),
                                                                     (3,1,2,'26.06.2020'),
                                                                     (2,3,2,'27.05.2020'),
                                                                     (4,7,1,'28.04.2020'),
                                                                     (3,4,1,'30.06.2020'),
                                                                     (2,5,1,'01.06.2020'),
                                                                     (1,6,2,'10.05.2020');



