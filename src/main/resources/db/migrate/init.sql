CREATE DATABASE petsrus;

USE petsrus;

CREATE TABLE owners (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    username VARCHAR(80) NOT NULL,
    contact_phone VARCHAR(80),
    email VARCHAR(80),
    premium_account BOOLEAN DEFAULT FALSE,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    elevation DOUBLE DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT NOW(),
    tracking_zone INTEGER DEFAULT 0

);


CREATE TABLE pets (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT(20) NOT NULL,
    pet_name VARCHAR(80) NOT NULL,
    collar_id VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    tracking_zone INTEGER DEFAULT 0
);


CREATE TABLE locations (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT(20) NOT NULL,
    pet_id BIGINT(20) NOT NULL,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    elevation DOUBLE DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT NOW(),
    tracking_zone INTEGER DEFAULT 0,
    FOREIGN KEY (owner_id) REFERENCES owners(id),
    FOREIGN KEY (pet_id) REFERENCES pets(id)
);

-- Insert test data
insert into owners (id, first_name,last_name, username, contact_phone, email, premium_account, longitude, latitude) values (1,"Premium", "Owner", "premium", "123-456-7890", "fred@dev.nul", 1,-119.0,37.639095);
insert into owners (id, first_name,last_name, username, contact_phone, email, premium_account, longitude, latitude) values (2,"One", "Pet", "one", "223-456-7890", "ted@dev.nul", 0,-119.0,37.639095);
insert into owners (id, first_name,last_name, username, contact_phone, email, premium_account, longitude, latitude) values (3,"Two", "Pets", "two", "323-456-7890", "frank@dev.nul", 0,-119.0,37.639095);


insert into pets (id,owner_id, pet_name, collar_id) values (1,1,"shadow","123315523422");
insert into pets (id,owner_id, pet_name, collar_id) values (2,2,"max", "434331553312");
insert into pets (id,owner_id, pet_name, collar_id) values (3,3,"rex", "545341553317");
insert into pets (id,owner_id, pet_name, collar_id) values (4,3,"meap", "764533453455");

-- Use the locations file to insert test locations


