# ModelSpark

base de datos

CREATE TABLE public.persona
(
  id bigserial,
  nombre character varying(50),
  apellido character varying(50),
  estado integer DEFAULT 1,
  CONSTRAINT pk_persona PRIMARY KEY (id)
)
