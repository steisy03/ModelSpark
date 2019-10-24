# ModelSpark

### base de datos (PostgreSQL)

```SQL
CREATE TABLE public.persona
(
  id bigserial,
  nombre character varying(50),
  apellido character varying(50),
  estado boolean DEFAULT true,
  CONSTRAINT pk_persona PRIMARY KEY (id)
);

CREATE TABLE public.tipo_persona
(
  id bigserial,
  descripcion character varying(20),
  CONSTRAINT pk_tipo_persona PRIMARY KEY (id)
);
```
### puerto por defecto (Spark): 4567
