DROP SCHEMA IF EXISTS oblig4r CASCADE;
CREATE SCHEMA oblig4r;
SET search_path = oblig4r;

CREATE TABLE deltagere 
(
   fornavn CHARACTER VARYING (40),
   etternavn character varying(40),
   mobilNr varchar (10),
	kjonn varchar(10),
   pwd_hash CHARACTER (64),
   pwd_salt CHARACTER (32),
   PRIMARY KEY (mobilNr)
);
INSERT INTO deltagere VALUES 
	('Per','Persen','12345678','mann', -- passord: qwerty
		'DF32FB5C3D132F276CA0E9B582ADA7E7B72CA1E5DE58C35D86C378A9446EE005',
		'38943AF5CA14AE5C1B9438FBB20233CA'), 
	('Pål','Andersen','87654321' ,'mann',-- passord: pass123
		'2C8700BDBD964E483E98BF371D8810D47F10C539B508D0ACCCC4416282873F49',
		'A8EEEB37FAAC34B399D8E4CE4C1DC72B');