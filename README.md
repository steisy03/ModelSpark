# ModelSpark

base de datos

CREATE TABLE public.persona
(
  id bigseral,
  nombre character varying(50),
  apellido character varying(50),
  estado boolean DEFAULT true,
  CONSTRAINT pk_persona PRIMARY KEY (id)
)
