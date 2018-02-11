DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(128) UNIQUE,
    password VARCHAR(256),
    enabled BOOL);
insert into users (username, password, enabled) values ('wfigas', 'pol', true);