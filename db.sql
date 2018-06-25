--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.4

-- Started on 2018-06-25 11:24:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2816 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 24576)
-- Name: tasks; Type: TABLE; Schema: public; Owner: bogosort
--

CREATE TABLE public.tasks (
    title text NOT NULL,
    description text,
    is_ok boolean,
    user_id integer NOT NULL,
    task_id integer NOT NULL
);


ALTER TABLE public.tasks OWNER TO bogosort;

--
-- TOC entry 197 (class 1259 OID 24582)
-- Name: tasks_task_id_seq; Type: SEQUENCE; Schema: public; Owner: bogosort
--

CREATE SEQUENCE public.tasks_task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_task_id_seq OWNER TO bogosort;

--
-- TOC entry 2817 (class 0 OID 0)
-- Dependencies: 197
-- Name: tasks_task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bogosort
--

ALTER SEQUENCE public.tasks_task_id_seq OWNED BY public.tasks.task_id;


--
-- TOC entry 198 (class 1259 OID 24584)
-- Name: usuario; Type: TABLE; Schema: public; Owner: bogosort
--

CREATE TABLE public.usuario (
    login text NOT NULL,
    senha text NOT NULL,
    user_id integer NOT NULL,
    picture text
);


ALTER TABLE public.usuario OWNER TO bogosort;

--
-- TOC entry 199 (class 1259 OID 24590)
-- Name: usuario_user_id_seq; Type: SEQUENCE; Schema: public; Owner: bogosort
--

CREATE SEQUENCE public.usuario_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_user_id_seq OWNER TO bogosort;

--
-- TOC entry 2818 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bogosort
--

ALTER SEQUENCE public.usuario_user_id_seq OWNED BY public.usuario.user_id;


--
-- TOC entry 2678 (class 2604 OID 24592)
-- Name: tasks task_id; Type: DEFAULT; Schema: public; Owner: bogosort
--

ALTER TABLE ONLY public.tasks ALTER COLUMN task_id SET DEFAULT nextval('public.tasks_task_id_seq'::regclass);


--
-- TOC entry 2679 (class 2604 OID 24593)
-- Name: usuario user_id; Type: DEFAULT; Schema: public; Owner: bogosort
--

ALTER TABLE ONLY public.usuario ALTER COLUMN user_id SET DEFAULT nextval('public.usuario_user_id_seq'::regclass);


--
-- TOC entry 2805 (class 0 OID 24576)
-- Dependencies: 196
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: bogosort
--

INSERT INTO public.tasks VALUES ('rar rar', 'rar rar', false, 2, 127);
INSERT INTO public.tasks VALUES ('okey', 'okey', false, 1, 80);
INSERT INTO public.tasks VALUES ('e o novo', 'e o novo', false, 1, 81);


--
-- TOC entry 2807 (class 0 OID 24584)
-- Dependencies: 198
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: bogosort
--

INSERT INTO public.usuario VALUES ('login1', '92f20dafc5e5ac1c66820903c492cc04', 1, 'C:\Users\Rafael Avilar\Documents\GitHub\todo-app\web\default.png');
INSERT INTO public.usuario VALUES ('login3', 'f940608acd624f8092bc86353052e734', 3, 'C:\Users\Rafael Avilar\Documents\GitHub\todo-app\web\default.png');
INSERT INTO public.usuario VALUES ('login2', '34ae07655b9a94e90556aff79bfd60b0', 2, './picture/7q7IXrX.jpg');


--
-- TOC entry 2819 (class 0 OID 0)
-- Dependencies: 197
-- Name: tasks_task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bogosort
--

SELECT pg_catalog.setval('public.tasks_task_id_seq', 127, true);


--
-- TOC entry 2820 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bogosort
--

SELECT pg_catalog.setval('public.usuario_user_id_seq', 3, true);


--
-- TOC entry 2681 (class 2606 OID 24595)
-- Name: tasks pkey_task; Type: CONSTRAINT; Schema: public; Owner: bogosort
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT pkey_task PRIMARY KEY (task_id);


--
-- TOC entry 2683 (class 2606 OID 24597)
-- Name: usuario pkey_usuario; Type: CONSTRAINT; Schema: public; Owner: bogosort
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pkey_usuario PRIMARY KEY (user_id);


-- Completed on 2018-06-25 11:24:11

--
-- PostgreSQL database dump complete
--

