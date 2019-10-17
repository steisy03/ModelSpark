# ModelSpark

base de datos (PostgreSQL)

CREATE TABLE public.persona
(
  id bigserial,
  nombre character varying(50),
  apellido character varying(50),
  estado boolean DEFAULT true,
  CONSTRAINT pk_persona PRIMARY KEY (id)
)

puerto por defecto (Spark): 4567
