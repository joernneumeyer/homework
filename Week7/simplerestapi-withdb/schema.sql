--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2
begin work;
--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

--
-- Name: en_de; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE words (
    english text primary key ,
    french text not null,
    constraint wordpair_un unique(english,french) 
);

insert into words values ('furious','furieux');

commit;
