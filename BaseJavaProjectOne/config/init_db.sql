-- Table: public.resume

-- DROP TABLE IF EXISTS public.resume;

CREATE TABLE IF NOT EXISTS public.resume
(
    uuid character varying(36) COLLATE pg_catalog."default" NOT NULL,
    full_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT resume_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.resume
    OWNER to postgres;


-- Table: public.contact

-- DROP TABLE IF EXISTS public.contact;

CREATE TABLE IF NOT EXISTS public.contact
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    type text COLLATE pg_catalog."default" NOT NULL,
    value text COLLATE pg_catalog."default" NOT NULL,
    resume_uuid character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT contact_pkey PRIMARY KEY (id),
    CONSTRAINT resume_contact_uuid_fk FOREIGN KEY (resume_uuid)
        REFERENCES public.resume (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.contact
    OWNER to postgres;

CREATE UNIQUE INDEX contact_uuid_type_index
    ON public.contact USING btree
    (resume_uuid COLLATE pg_catalog."C" text_pattern_ops ASC NULLS LAST)
    INCLUDE(type)
;