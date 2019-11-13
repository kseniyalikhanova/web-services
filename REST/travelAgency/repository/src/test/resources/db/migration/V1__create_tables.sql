CREATE DATABASE travel_agency;
CREATE SCHEMA travel_agency;

CREATE TABLE IF NOT EXISTS travel_agency.user
(
  id       SERIAL PRIMARY KEY,
login    character varying(100) NOT NULL UNIQUE ,
password character varying  NOT NULL,
is_admin smallint DEFAULT 0
);
ALTER SEQUENCE travel_agency.user_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS travel_agency.country
(
  id   SERIAL PRIMARY KEY,
name character varying(60) NOT NULL UNIQUE
is_archival smallint DEFAULT 0
);
ALTER SEQUENCE travel_agency.country_id_seq RESTART WITH 1;

CREATE TYPE feature AS ENUM ('HEATING', 'KITCHEN', 'HANGERS', 'HAIR DRYER', 'WIFI',
'GYM', 'POOL', 'IRON', 'BREAKFAST', 'TV');
CREATE TABLE IF NOT EXISTS travel_agency.hotel
(
  id        SERIAL PRIMARY KEY,
name      character varying(60) NOT NULL,
stars     smallint              NOT NULL,
website   character varying(63) NOT NULL,
latitude  double precision      NOT NULL CHECK (latitude > -90 and latitude <= 90),
longitude double precision      NOT NULL CHECK (longitude > -180 and longitude <= 180),
features  feature[]
is_archival smallint DEFAULT 0
);
ALTER SEQUENCE travel_agency.hotel_id_seq RESTART WITH 1;

CREATE TYPE tour_type AS ENUM ('ECOTOURISM_HOLIDAYS', 'WILDLIFE_AND_NATURE_HOLIDAYS',
'AFRICAN_SAFARI_HOLIDAYS', 'WELLNESS_AND_SPA_HOLIDAY',
'CRUISE_HOLIDAYS', 'WALKING_AND_ACTIVE_HOLIDAYS', 'CULTURAL_HOLIDAYS',
'GROUP_HOLIDAYS', 'LUXURY_BEACH_HOLIDAYS', 'THE_WEEKEND_BREAK');
CREATE TABLE IF NOT EXISTS travel_agency.tour
(
  id          SERIAL PRIMARY KEY,
photo       character varying                         NOT NULL,
date        date                                      NOT NULL,
duration    smallint                                  NOT NULL,
description text,
cost        numeric                                   NOT NULL CHECK (cost > 0),
tour_type   tour_type                                 NOT NULL,
hotel_id    int references travel_agency.hotel (id)   NOT NULL,
country_id  int references travel_agency.country (id) NOT NULL
is_archival smallint DEFAULT 0
);
ALTER SEQUENCE travel_agency.tour_id_seq RESTART WITH 1;


CREATE TABLE IF NOT EXISTS travel_agency.user_tour
(
  id      SERIAL PRIMARY KEY,
  user_id int NOT NULL,
  tour_id int NOT NULL
);
ALTER SEQUENCE travel_agency.user_tour_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS travel_agency.review
(
  id      SERIAL PRIMARY KEY,
  date    date                                   NOT NULL DEFAULT NOW(),
text    text                                   NOT NULL,
user_id int references travel_agency.user (id) NOT NULL,
tour_id int references travel_agency.tour (id) NOT NULL
is_archival smallint DEFAULT 0
);
ALTER SEQUENCE travel_agency.review_id_seq RESTART WITH 1;