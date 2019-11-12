CREATE DATABASE travel_agency
    WITH
    OWNER = postgres
    ENCODING = 'UTF8';

CREATE SCHEMA travel_agency
    AUTHORIZATION postgres;

GRANT ALL ON SCHEMA travel_agency TO postgres;

GRANT ALL ON SCHEMA travel_agency TO PUBLIC;