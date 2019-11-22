BEGIN TRANSACTION;

DROP TABLE IF EXISTS paraderos;
CREATE TABLE paraderos (
    pk bigserial NOT NULL,
    codigo varchar(255) NOT NULL,
    direccion text,
    latitud double precision NOT NULL DEFAULT '0.0',
    longitud double precision NOT NULL DEFAULT '0.0',
    UNIQUE (codigo),
    PRIMARY KEY (pk)
);
CREATE UNIQUE INDEX ON paraderos(LOWER(TRIM(both FROM codigo)));



DROP TABLE IF EXISTS microbuses CASCADE;
CREATE TABLE microbuses (
    pk bigserial NOT NULL,
    recorrido varchar(255) NOT NULL,
    tipo int NOT NULL  DEFAULT '0',
    UNIQUE (recorrido, tipo),
    PRIMARY KEY (pk)
);


DROP TABLE IF EXISTS paradas CASCADE;
CREATE TABLE paradas (
    pk bigserial NOT NULL,
    micro_fk bigint NOT NULL,
    paradero_fk bigint NOT NULL,
    FOREIGN KEY (micro_fk) REFERENCES microbuses(pk) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (paradero_fk) REFERENCES paraderos(pk) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE (micro_fk, paradero_fk),
    PRIMARY KEY (pk)
);



COMMIT;
