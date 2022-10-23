INSERT INTO PUBLIC.FILMS (TITLE, DESCRIPTION, RELEASE_DATE, DURATION, RATE, MPA_RATING)
VALUES ('film_title_1', 'film_description_1', '2022-10-06', 101, 1, 'NC-17');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (1, 'Комедия');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (2, 'Драма');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (3, 'Мультфильм');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (4, 'Триллер');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (5, 'Документальный');

INSERT INTO PUBLIC.GENRE (GENRE_ID, GENRE_NAME)
VALUES (6, 'Боевик');

INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
VALUES ('email1@amail.ru', null, 'name_1', '2020-01-01');

INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
VALUES ('email2@amail.ru', null, 'name_2', '2020-02-02');

INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
VALUES ('email3@amail.ru', null, 'name_3', '2020-03-03');

