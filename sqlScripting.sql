DROP TABLE IF EXISTS label_signed;
DROP TABLE IF EXISTS application_user;
DROP TABLE IF EXISTS artisti;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS label;

CREATE TABLE genre (
    genreid BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL
);

INSERT INTO genre (name)
VALUES
    ('Alt-rock'),
    ('Metal'),
    ('Rock');

CREATE TABLE label (
    labelid BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL 
);

INSERT INTO label (name)
VALUES
    ('Spinefarm'),
    ('Nuclear Blast'),
    ('Atlantic'),
    ('Fueled by Ramen'),
    ('Elektra');

CREATE TABLE application_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(250) NOT NULL UNIQUE,
    role VARCHAR(100) NOT NULL,
    password VARCHAR(250) NOT NULL
);

INSERT INTO application_user (username, password, role)
VALUES
    ('admin', '$2a$12$s3spzn7imeVp5o0f6zv6AuDWSOjHJmdRRZXo8O0ibOiVcz2UAZtHi', 'ADMIN'),
    ('user', '$2a$12$m3HNDX/8FOovrv8jvbWXneZXm5.yK4/SDMColosEVy.IBWCCUCBD6', 'USER');

CREATE TABLE artisti (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    country VARCHAR(100),
    founded INTEGER,
    genreid BIGINT,
    CONSTRAINT fk_artist_genre
        FOREIGN KEY (genreid) REFERENCES genre(genreID)
);

INSERT INTO artisti (name, country, founded, genreid)
    VALUES
    ('Nightwish', 'Finland', 1996, 2),
    ('21 Pilots', 'USA', 2009, 1),
    ('AC/DC', 'Australia', 1973, 3);

CREATE TABLE label_signed (
    id BIGINT NOT NULL,
    labelid BIGINT NOT NULL,
    PRIMARY KEY (id, labelid),
    CONSTRAINT fk_artist
        FOREIGN KEY (id) REFERENCES artisti(id),
    CONSTRAINT fk_label
        FOREIGN KEY (labelid) REFERENCES label(labelid)
);

INSERT INTO label_signed(id, labelid)
    VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 3),
    (3, 5);