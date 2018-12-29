CREATE TABLE PARTY (
	PARTY_ID BIGSERIAL NOT NULL,
	PARTY_TYPE_ID SMALLINT
);

ALTER TABLE PARTY ADD CONSTRAINT PK_PARTY
	PRIMARY KEY (PARTY_ID);
	
CREATE TABLE PERSON (
	PARTY_ID BIGINT,
	CURRENT_LAST_NAME VARCHAR(100),
	CURRENT_FIRST_NAME VARCHAR(100),
	BIRTHDATE DATE,
	GENDER CHAR(1)
);

ALTER TABLE PERSON ADD CONSTRAINT PK_PERSON
	PRIMARY KEY (PARTY_ID);

ALTER TABLE PERSON ADD CONSTRAINT FK_PERSON_PARTY
	FOREIGN KEY (PARTY_ID) REFERENCES PARTY (PARTY_ID)
ON DELETE CASCADE;

CREATE TABLE FINANCIAL_ACCOUNT_TYPE (
	FINANCIAL_ACCOUNT_TYPE_ID SMALLINT NOT NULL,
	DESCRIPTION VARCHAR(50) NOT NULL
);

ALTER TABLE FINANCIAL_ACCOUNT_TYPE ADD CONSTRAINT PK_FINANCIAL_ACCOUNT_TYPE
	PRIMARY KEY (FINANCIAL_ACCOUNT_TYPE_ID);

INSERT INTO FINANCIAL_ACCOUNT_TYPE (FINANCIAL_ACCOUNT_TYPE_ID, DESCRIPTION)
VALUES(1, 'BANK_ACCOUNT');

INSERT INTO FINANCIAL_ACCOUNT_TYPE (FINANCIAL_ACCOUNT_TYPE_ID, DESCRIPTION)
VALUES(2, 'INVESTMENT_ACCOUNT');

CREATE TABLE FINANCIAL_ACCOUNT (
	FINANCIAL_ACCOUNT_ID BIGSERIAL NOT NULL,
	FINANCIAL_ACCOUNT_NAME VARCHAR(50),
	FINANCIAL_ACCOUNT_TYPE_ID SMALLINT
);

ALTER TABLE FINANCIAL_ACCOUNT ADD CONSTRAINT PK_FINANCIAL_ACCOUNT
	PRIMARY KEY (FINANCIAL_ACCOUNT_ID);

ALTER TABLE FINANCIAL_ACCOUNT ADD CONSTRAINT FK_FINANCIAL_ACCOUNT_FINANCIAL_ACCOUNT_TYPE
	FOREIGN KEY (FINANCIAL_ACCOUNT_TYPE_ID) REFERENCES FINANCIAL_ACCOUNT_TYPE (FINANCIAL_ACCOUNT_TYPE_ID)
ON DELETE CASCADE;

CREATE TABLE BANK_ACCOUNT (
	FINANCIAL_ACCOUNT_ID BIGINT NOT NULL
);

ALTER TABLE BANK_ACCOUNT ADD CONSTRAINT PK_BANK_ACCOUNT
	PRIMARY KEY (FINANCIAL_ACCOUNT_ID);

ALTER TABLE BANK_ACCOUNT ADD CONSTRAINT FK_BANK_ACCOUNT_FINANCIAL_ACCOUNT
	FOREIGN KEY (FINANCIAL_ACCOUNT_ID) REFERENCES FINANCIAL_ACCOUNT (FINANCIAL_ACCOUNT_ID)
ON DELETE CASCADE;

CREATE TABLE FINANCIAL_ACCOUNT_TRANSACTION_TYPE (
	FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID SMALLINT NOT NULL,
	DESCRIPTION VARCHAR(50)
);

ALTER TABLE FINANCIAL_ACCOUNT_TRANSACTION_TYPE ADD CONSTRAINT PK_FINANCIAL_ACCOUNT_TRANSACTION_TYPE
	PRIMARY KEY (FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID);

INSERT INTO FINANCIAL_ACCOUNT_TRANSACTION_TYPE (FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID, DESCRIPTION)
VALUES(1, 'WITHDRAWAL');

INSERT INTO FINANCIAL_ACCOUNT_TRANSACTION_TYPE (FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID, DESCRIPTION)
VALUES(2, 'DEPOSIT');

CREATE TABLE FINANCIAL_ACCOUNT_TRANSACTION (
	FINANCIAL_ACCOUNT_TRANSACTION_ID BIGSERIAL NOT NULL,
	FINANCIAL_ACCOUNT_ID BIGINT NOT NULL,
	PARTY_ID BIGINT NOT NULL,
	TRANSACTION_DATE TIMESTAMP NOT NULL,
	ENTRY_DATE TIMESTAMP,
	FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID SMALLINT NOT NULL
);

ALTER TABLE FINANCIAL_ACCOUNT_TRANSACTION ADD CONSTRAINT PK_FINANCIAL_ACCOUNT_TRANSACTION
	PRIMARY KEY (FINANCIAL_ACCOUNT_TRANSACTION_ID);

ALTER TABLE FINANCIAL_ACCOUNT_TRANSACTION ADD CONSTRAINT FK_FINANCIAL_ACCOUNT_TRANSACTION_FINANCIAL_ACCOUNT
	FOREIGN KEY (FINANCIAL_ACCOUNT_ID) REFERENCES FINANCIAL_ACCOUNT (FINANCIAL_ACCOUNT_ID)
ON DELETE CASCADE;

ALTER TABLE FINANCIAL_ACCOUNT_TRANSACTION ADD CONSTRAINT FK_FINANCIAL_ACCOUNT_TRANSACTION_FINANCIAL_ACCOUNT_TRANSACTION_TYPE
	FOREIGN KEY (FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID) REFERENCES FINANCIAL_ACCOUNT_TRANSACTION_TYPE (FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID)
ON DELETE CASCADE;

CREATE TABLE WITHDRAWAL (
	FINANCIAL_ACCOUNT_TRANSACTION_ID BIGINT NOT NULL
);

ALTER TABLE WITHDRAWAL ADD CONSTRAINT PK_WITHDRAWAL
	PRIMARY KEY (FINANCIAL_ACCOUNT_TRANSACTION_ID);

ALTER TABLE WITHDRAWAL ADD CONSTRAINT FK_WITHDRAWAL_FINANCIAL_ACCOUNT_TRANSACTION
	FOREIGN KEY (FINANCIAL_ACCOUNT_TRANSACTION_ID) REFERENCES FINANCIAL_ACCOUNT_TRANSACTION (FINANCIAL_ACCOUNT_TRANSACTION_ID)
ON DELETE CASCADE;	

CREATE TABLE DEPOSIT (
	FINANCIAL_ACCOUNT_TRANSACTION_ID BIGINT NOT NULL
);

ALTER TABLE DEPOSIT ADD CONSTRAINT PK_DEPOSIT
	PRIMARY KEY (FINANCIAL_ACCOUNT_TRANSACTION_ID);

ALTER TABLE DEPOSIT ADD CONSTRAINT FK_DEPOSIT_FINANCIAL_ACCOUNT_TRANSACTION
	FOREIGN KEY (FINANCIAL_ACCOUNT_TRANSACTION_ID) REFERENCES FINANCIAL_ACCOUNT_TRANSACTION (FINANCIAL_ACCOUNT_TRANSACTION_ID)
ON DELETE CASCADE;

CREATE TABLE PAYMENT_TYPE (
	PAYMENT_TYPE_ID SMALLINT NOT NULL,
	DESCRIPTION VARCHAR(50) NOT NULL
);

ALTER TABLE PAYMENT_TYPE ADD CONSTRAINT PK_PAYMENT_TYPE
	PRIMARY KEY (PAYMENT_TYPE_ID);
	
INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, DESCRIPTION)
VALUES(1, 'RECEIPT');

INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, DESCRIPTION)
VALUES(2, 'DISBURSMENT');

CREATE TABLE PAYMENT_METHOD_TYPE (
	PAYMENT_METHOD_TYPE_ID SMALLINT NOT NULL,
	DESCRIPTION VARCHAR(50) NOT NULL
);

ALTER TABLE PAYMENT_METHOD_TYPE ADD CONSTRAINT PK_PAYMENT_METHOD_TYPE
	PRIMARY KEY (PAYMENT_METHOD_TYPE_ID);
	
INSERT INTO PAYMENT_METHOD_TYPE (PAYMENT_METHOD_TYPE_ID, DESCRIPTION)
VALUES(1, 'CASH');
	
INSERT INTO PAYMENT_METHOD_TYPE (PAYMENT_METHOD_TYPE_ID, DESCRIPTION)
VALUES(2, 'CREDIT CARD');	

CREATE TABLE PAYMENT (
	PAYMENT_ID BIGSERIAL NOT NULL,
	EFFECTIVE_DATE TIMESTAMP NOT NULL,
	AMOUNT MONEY NOT NULL,
	PARTY_ID_TO BIGINT NOT NULL,
	PARTY_ID_FROM BIGINT NOT NULL,
	COMMENT VARCHAR(50),
	PAYMENT_TYPE_ID SMALLINT NOT NULL,
	PAYMENT_METHOD_TYPE_ID SMALLINT
);

ALTER TABLE PAYMENT ADD CONSTRAINT PK_PAYMENT
	PRIMARY KEY (PAYMENT_ID);

ALTER TABLE PAYMENT ADD CONSTRAINT FK_PAYMENT_PAYMENT_METHOD_TYPE
	FOREIGN KEY (PAYMENT_METHOD_TYPE_ID) REFERENCES PAYMENT_METHOD_TYPE (PAYMENT_METHOD_TYPE_ID)
ON DELETE CASCADE;	

ALTER TABLE PAYMENT ADD CONSTRAINT FK_PAYMENT_PARTY_TO
	FOREIGN KEY (PARTY_ID_TO) REFERENCES PARTY (PARTY_ID)
ON DELETE CASCADE;	

ALTER TABLE PAYMENT ADD CONSTRAINT FK_PAYMENT_PARTY_FROM
	FOREIGN KEY (PARTY_ID_FROM) REFERENCES PARTY (PARTY_ID)
ON DELETE CASCADE;	
