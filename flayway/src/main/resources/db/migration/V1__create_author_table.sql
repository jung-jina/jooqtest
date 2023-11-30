CREATE TABLE author (
                                    id INT NOT NULL,
                                    first_name VARCHAR(50),
                                    last_name VARCHAR(50) NOT NULL,
                                    datetime timestamp without time zone NOT NULL,
                                    CONSTRAINT pk_author PRIMARY KEY (ID)
);