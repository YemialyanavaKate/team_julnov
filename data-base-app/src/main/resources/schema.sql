CREATE TABLE FRIDGE (number INT PRIMARY KEY, type VARCHAR (255), description VARCHAR (255), discount BOOLEAN, defect BOOLEAN, price DECIMAL, energy CHAR (255), registered TIMESTAMP WITH TIME ZONE, kettle_number INT);
CREATE TABLE KETTLE (number INT PRIMARY KEY, type VARCHAR (255), color VARCHAR (255), isElectric BOOLEAN, isInduction BOOLEAN, price DECIMAL, energy CHAR (255), registered TIMESTAMP WITH TIME ZONE);
CREATE TABLE MULTICOOKER (number INT PRIMARY KEY, type VARCHAR (255), description VARCHAR (255), isTouchScreen BOOLEAN, numberModes INT, price DECIMAL, energy CHAR (255), registered TIMESTAMP WITH TIME ZONE, fridge_number INT);
CREATE TABLE TV (number INT PRIMARY KEY, type VARCHAR (255), brand VARCHAR (255), discount BOOLEAN, diagonal INT, price DECIMAL, energy CHAR (255), registered TIMESTAMP WITH TIME ZONE, country_id INT);
CREATE TABLE COUNTRY (id INT PRIMARY KEY, country INT, tv_number INT REFERENCES TV (number));
CREATE TABLE KTTL_TV (kttl_number INT, tv_number INT);
CREATE TABLE TV_KETTLES (tv_number INT, kettles_number INT);