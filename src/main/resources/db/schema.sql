DROP TABLE IF EXISTS people;

CREATE TABLE IF NOT EXISTS people (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    birth_date DATE,
    birth_time TIME,
    birth_place VARCHAR(200),
    category VARCHAR(50),
    extra_info VARCHAR(200)
);