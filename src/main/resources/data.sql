DELETE FROM MPA;

INSERT INTO PUBLIC.MPA (MPA_ID, MPA_NAME)
VALUES (1, 'G' );

INSERT INTO PUBLIC.MPA (MPA_ID, MPA_NAME)
VALUES (2, 'PG' );

INSERT INTO PUBLIC.MPA (MPA_ID, MPA_NAME)
VALUES (3, 'PG-13' );

INSERT INTO PUBLIC.MPA (MPA_ID, MPA_NAME)
VALUES (4, 'R' );

INSERT INTO PUBLIC.MPA (MPA_ID, MPA_NAME)
VALUES (5, 'NC-17' );

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (1, 'Комедия');

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (2, 'Драма');

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (3, 'Мультфильм');

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (4, 'Триллер');

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (5, 'Документальный');

INSERT INTO PUBLIC.GENRES (GENRE_ID, GENRE_NAME)
VALUES (6, 'Боевик');

-- INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
-- VALUES ('email1@amail.ru', 'login_1', 'name_1', '2020-01-01');
--
-- INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
-- VALUES ('email2@amail.ru', 'login_2', 'name_2', '2020-02-02');
--
-- INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY)
-- VALUES ('email3@amail.ru', 'login_3', 'name_3', '2020-03-03');
--
-- INSERT INTO PUBLIC.FILMS (TITLE, DESCRIPTION, RELEASE_DATE, DURATION, RATE, MPA_ID)
-- VALUES ('film_title_1', 'film_description_1', '2001-01-01', 101, 1, 1);
--
-- INSERT INTO PUBLIC.FILMS (TITLE, DESCRIPTION, RELEASE_DATE, DURATION, RATE, MPA_ID)
-- VALUES ( 'film_title_2', 'film_description_2', '2002-02-02', 102, 2, 2);
--
-- INSERT INTO PUBLIC.FILMS (TITLE, DESCRIPTION, RELEASE_DATE, DURATION, RATE, MPA_ID)
-- VALUES ('film_title_3', 'film_description_3', '2003-03-03', 103, 3, 3);

-- INSERT INTO PUBLIC.FILM_GENRE (FILM_ID, GENRE_ID) VALUES ( 1,1 );
--
-- INSERT INTO PUBLIC.FILM_GENRE (FILM_ID, GENRE_ID) VALUES ( 1,4 );
--
-- INSERT INTO PUBLIC.FILM_GENRE (FILM_ID, GENRE_ID) VALUES ( 1,6 );
--
-- INSERT INTO PUBLIC.FILM_GENRE (FILM_ID, GENRE_ID) VALUES ( 2,2 );
--
-- INSERT INTO PUBLIC.FILM_GENRE (FILM_ID, GENRE_ID) VALUES ( 2,3 );

DELETE FROM FRIENDSHIP;

DELETE FROM PUBLIC.USERS;



