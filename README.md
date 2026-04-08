# novobase
prueba tecnica


# Autor : Ángel Santiago Hernández Solórzano

## Introducción
Estás desarrollando un microservicio que consta de tres capas:
Capa de persistencia – un paquete dao responsable de recuperar data representada por las entidades Item, Review y User en el paquete model
Capa de servicio – un paquete service responsable de la lógica del negocio
Capa web – un paquete controller que sirve como endpoint REST

## Definición de la tarea
Por favor implemente los siguientes métodos:
ItemRepository List<Item> findItemsWithAverageRatingLowerThan(Double rating) – el método deberá encontrar todos los items que tienen un rating menor que el rating pasado como argumento. Use los reviews asociados con cada item para calcular el rating del item. Si no hay ningún review para un item, entonces su rating deberá ser cero. Su implementación deberá hacer la menor cantidad de viajes de ida y vuelta a la base de datos.
ItemService List<String> getTitles(Double rating) – el método deberá recuperar data usando ItemRepository y devolverá solo los títulos de los ítems.
ItemController List<String> getTitles(Double rating) – el métodos deberá proveer un endpoint REST (método GET) bajo la ruta '/titles' y devolverá títulos del ItemService en formato JSON con codificación UTF8

## Diagrama entidad relacion

<img width="531" height="364" alt="image" src="https://github.com/user-attachments/assets/662be76d-1055-4f6e-b2ef-43c12b6540c3" />

## DDL

```sql
-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';
-- public.accountuser definition

-- Drop table

-- DROP TABLE public.accountuser;

CREATE TABLE public.accountuser (
	idaccount int8 NOT NULL,
	nickname varchar NULL,
	"password" varchar NULL,
	CONSTRAINT accountuser_pk PRIMARY KEY (idaccount)
);

-- Permissions

ALTER TABLE public.accountuser OWNER TO postgres;
GRANT ALL ON TABLE public.accountuser TO postgres;
GRANT ALL ON TABLE public.accountuser TO anon;
GRANT ALL ON TABLE public.accountuser TO authenticated;
GRANT ALL ON TABLE public.accountuser TO service_role;


-- public.item definition

-- Drop table

-- DROP TABLE public.item;

CREATE TABLE public.item (
	iditem int8 NOT NULL,
	titulo varchar NULL,
	descripcion varchar NULL,
	CONSTRAINT item_pk PRIMARY KEY (iditem)
);

-- Permissions

ALTER TABLE public.item OWNER TO postgres;
GRANT ALL ON TABLE public.item TO postgres;
GRANT ALL ON TABLE public.item TO anon;
GRANT ALL ON TABLE public.item TO authenticated;
GRANT ALL ON TABLE public.item TO service_role;


-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	iduser int8 NOT NULL,
	idaccount_fk int8 NULL,
	nombre varchar NULL,
	apellido varchar NULL,
	"document" int8 NULL,
	CONSTRAINT user_pk PRIMARY KEY (iduser),
	CONSTRAINT user_accountuser_fk FOREIGN KEY (idaccount_fk) REFERENCES public.accountuser(idaccount)
);

-- Permissions

ALTER TABLE public."user" OWNER TO postgres;
GRANT ALL ON TABLE public."user" TO postgres;
GRANT ALL ON TABLE public."user" TO anon;
GRANT ALL ON TABLE public."user" TO authenticated;
GRANT ALL ON TABLE public."user" TO service_role;


-- public.review definition

-- Drop table

-- DROP TABLE public.review;

CREATE TABLE public.review (
	idreview int8 NOT NULL,
	iditem_fk int8 NULL,
	iduser_fk int8 NULL,
	cantidad int8 NULL,
	ultimoreview timestamp NULL,
	CONSTRAINT review_pk PRIMARY KEY (idreview),
	CONSTRAINT review_item_fk FOREIGN KEY (iditem_fk) REFERENCES public.item(iditem),
	CONSTRAINT review_user_fk FOREIGN KEY (iduser_fk) REFERENCES public."user"(iduser)
);

-- Permissions

ALTER TABLE public.review OWNER TO postgres;
GRANT ALL ON TABLE public.review TO postgres;
GRANT ALL ON TABLE public.review TO anon;
GRANT ALL ON TABLE public.review TO authenticated;
GRANT ALL ON TABLE public.review TO service_role;


-- public.item_with_rating source

CREATE OR REPLACE VIEW public.item_with_rating
AS SELECT i.iditem,
    i.titulo,
    i.descripcion,
    COALESCE(avg(r.cantidad), 0::numeric) AS rating
   FROM item i
     LEFT JOIN review r ON i.iditem = r.idreview
  GROUP BY i.iditem, i.titulo, i.descripcion
  ORDER BY (COALESCE(avg(r.cantidad), 0::numeric));

-- Permissions

ALTER TABLE public.item_with_rating OWNER TO postgres;
GRANT ALL ON TABLE public.item_with_rating TO postgres;
GRANT ALL ON TABLE public.item_with_rating TO anon;
GRANT ALL ON TABLE public.item_with_rating TO authenticated;
GRANT ALL ON TABLE public.item_with_rating TO service_role;

-- Permissions

GRANT ALL ON SCHEMA public TO pg_database_owner;
GRANT ALL ON SCHEMA public TO public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO anon;
GRANT USAGE ON SCHEMA public TO authenticated;
GRANT USAGE ON SCHEMA public TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT, DELETE, TRUNCATE, TRIGGER, REFERENCES, SELECT, UPDATE, UNKNOWN ON TABLES TO service_role;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO anon;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO authenticated;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO service_role;
```
## Configuracion conexion a la DB

1. En un principio debemos contar con un gestor de db para realizar dicha conexion en este caso se uso DBeaver
2. En en el panel izquierdo debemos realizar click para observar la opcion de "crear nueva conexion"
3. Seguido a esto seleccionamos la opcion "PostgreSQL"
   <img width="670" height="632" alt="image" src="https://github.com/user-attachments/assets/71a1cd44-0e8c-491e-9647-96bf6496cba8" />

4. En el interior de este procedemos realizar el llenado de los siguientes datos

postgresql://postgres.xviqxmxeixigejkrojwg:[YOUR-PASSWORD]@aws-1-us-east-2.pooler.supabase.com:5432/postgres

° host:aws-1-us-east-2.pooler.supabase.com
° port:5432
° database:postgres
° user:postgres.xviqxmxeixigejkrojwg

<img width="1031" height="606" alt="image" src="https://github.com/user-attachments/assets/f525384b-6228-45d5-8f5d-7df5f18c83cc" />  
<img width="405" height="213" alt="image" src="https://github.com/user-attachments/assets/7e9bb14c-285d-4d3f-96ec-8d0d6a5702af" />

Nota: al haber realizado la conexion en este caso ya encontraremos en en esquema public las tablas creadas

<img width="629" height="599" alt="image" src="https://github.com/user-attachments/assets/fa27afe4-48f8-40ab-9788-b67edcc61ebe" />

## Implementacion de documentacion swagger

Ahora bien y para este caso se realizo la implementacion de swagger para asi realizar la prueba de los endpont expuestos y creados

Link :https://novobase.onrender.com/swagger-ui/index.html#/

<img width="1900" height="1022" alt="image" src="https://github.com/user-attachments/assets/709ba831-eb82-44cd-bd21-f1b783429cdd" />


Nota : al ser este un servicio expuesto y gratiut, en el momento de subir para su consumo se debe esperar un momento para el despliegue del mismo
<img width="1856" height="861" alt="image" src="https://github.com/user-attachments/assets/8d0cff36-7911-4746-8350-e9e3414ec53b" />

## Implementacion de pruebas unitarias

Para este caso se implementaron pruebas unitarias utilizando Mockito y JUnit 5, permitiendo validar el comportamiento del servicio de manera aislada mediante el uso de mocks del repositorio.

Link : https://novobase.onrender.com/index.html

<img width="1309" height="448" alt="image" src="https://github.com/user-attachments/assets/22f0bd3f-5aaa-44d6-b222-9486c6d6ac5e" />
<img width="937" height="294" alt="image" src="https://github.com/user-attachments/assets/3396c4a9-f756-4491-a679-bb1b7e53a93d" />
<img width="1039" height="237" alt="image" src="https://github.com/user-attachments/assets/837e6e95-bd17-4e94-b817-b9e961fa5654" />
<img width="607" height="870" alt="image" src="https://github.com/user-attachments/assets/735f21df-cbd8-40c7-9d05-bacabcb65a44" />



