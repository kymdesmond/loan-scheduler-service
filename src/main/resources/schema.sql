-- CREATE BANK TABLE
CREATE TABLE BANK
(
    BANK_ID      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    NAME         VARCHAR(100) NOT NULL,
    DATE_CREATED TIMESTAMP,
    CONSTRAINT PK_BANK PRIMARY KEY (BANK_ID)
);

-- CREATE PRODUCT TABLE
CREATE TABLE PRODUCT
(
    PRODUCT_ID       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    BANK_ID_FK       BIGINT NOT NULL,
    NAME             VARCHAR(100),
    FIXED_RATE       DOUBLE PRECISION,
    REDUCING_BALANCE DOUBLE PRECISION,
    DATE_CREATED     TIMESTAMP,
    CONSTRAINT PK_PRODUCT PRIMARY KEY (PRODUCT_ID)
);

ALTER TABLE PRODUCT
    ADD CONSTRAINT FK_PRODUCT_ON_BANK_ID_FK FOREIGN KEY (BANK_ID_FK) REFERENCES BANK (BANK_ID);

-- CREATE PRODUCT CHARGES TABLE
CREATE TABLE PRODUCT_CHARGES
(
    PRODUCT_CHARGE_ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    NAME              VARCHAR(100)                            NOT NULL,
    CHARGE_VALUE      DOUBLE PRECISION                        NOT NULL,
    CHARGE_TYPE       VARCHAR(100)                            NOT NULL,
    PERCENTAGE_ON     VARCHAR(100),
    CHARGE_ID         BIGINT,
    DATE_CREATED      TIMESTAMP,
    PRODUCT_ID_FK     BIGINT,
    CONSTRAINT PK_PRODUCT_CHARGES PRIMARY KEY (PRODUCT_CHARGE_ID)
);

ALTER TABLE PRODUCT_CHARGES
    ADD CONSTRAINT FK_PRODUCT_CHARGES_ON_PRODUCT_ID_FK FOREIGN KEY (PRODUCT_ID_FK) REFERENCES PRODUCT (PRODUCT_ID);

-- CREATE USERS TABLE
CREATE TABLE USERS
(
    USER_ID      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    USERNAME     VARCHAR(100),
    PASSWORD     VARCHAR(255) NOT NULL,
    DATE_CREATED TIMESTAMP,
    CONSTRAINT PK_USERS PRIMARY KEY (USER_ID)
);

ALTER TABLE USERS
    ADD CONSTRAINT UC_USERS_USERNAME UNIQUE (USERNAME);