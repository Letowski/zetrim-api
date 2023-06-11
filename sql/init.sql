-- schema owner
CREATE USER secmes WITH password 'secmes' NOSUPERUSER NOCREATEROLE NOCREATEDB;

CREATE DATABASE "secmes" OWNER secmes CONNECTION LIMIT 100;

\connect "secmes";

-- schema user
CREATE USER secmes_user WITH password 'secmes_user';


-- create schema
CREATE SCHEMA secmes AUTHORIZATION secmes;

--add-extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER EXTENSION "uuid-ossp" SET SCHEMA secmes;


--add-privileges
GRANT USAGE ON SCHEMA secmes TO secmes_user;


ALTER DEFAULT PRIVILEGES FOR USER secmes IN SCHEMA secmes GRANT SELECT,INSERT,UPDATE,DELETE  ON TABLES TO secmes_user;
ALTER DEFAULT PRIVILEGES FOR USER secmes IN SCHEMA secmes GRANT USAGE ON SEQUENCES TO secmes_user;
ALTER DEFAULT PRIVILEGES FOR USER secmes IN SCHEMA secmes GRANT EXECUTE ON FUNCTIONS  TO secmes_user;

ALTER DATABASE secmes SET DEFAULT_TRANSACTION_ISOLATION TO 'read committed';
create extension pgcrypto;
