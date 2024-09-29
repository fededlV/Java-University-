CREATE SCHEMA IF NOT EXISTS backend;
SET SCHEMA backend;

CREATE TABLE backend.repository
(
    ID              INT AUTO_INCREMENT NOT NULL,
    OWNER_ID        INT                NOT NULL,
    REPOSITORY_NAME VARCHAR(255)       NOT NULL,
    DESCRIPTION     VARCHAR(255)       NOT NULL,
    LAST_UPDATE     TIMESTAMP           NOT NULL,
    URL             VARCHAR(255)       NOT NULL,
    STARS           REAL            NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (OWNER_ID) REFERENCES backend.user (ID)
);

CREATE TABLE backend.user
(
    ID   INT AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255)       NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE backend.language
(
    ID   INT AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255)       NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE backend.language_repository
(
    ID            INT AUTO_INCREMENT NOT NULL,
    ID_LANGUAGE   INT                NOT NULL,
    ID_REPOSITORY INT                NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_LANGUAGE) REFERENCES backend.language (ID),
    FOREIGN KEY (ID_REPOSITORY) REFERENCES backend.repository (ID),
    UNIQUE (ID_LANGUAGE, ID_REPOSITORY)
);

CREATE TABLE backend.tag
(
    ID            INT AUTO_INCREMENT NOT NULL,
    NAME          VARCHAR(255)       NOT NULL,
    PRIMARY KEY (ID),
);

CREATE TABLE backend.tag_repository
(
    ID            INT AUTO_INCREMENT NOT NULL,
    ID_TAG        INT                NOT NULL,
    ID_REPOSITORY INT                NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_TAG) REFERENCES backend.tag (ID),
    FOREIGN KEY (ID_REPOSITORY) REFERENCES backend.repository (ID),
    UNIQUE (ID_TAG, ID_REPOSITORY)
);
