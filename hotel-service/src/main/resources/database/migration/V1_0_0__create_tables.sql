CREATE TABLE IF NOT EXISTS hotels(
    hotel_id BIGINT PRIMARY KEY NOT NULL,
    hotel_name VARCHAR(40) NOT NULL,
    hotel_description VARCHAR(255),
    hotel_address VARCHAR(255) NOT NULL,
    hotel_type VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS rooms (
     CONSTRAINT room_id PRIMARY KEY (hotel_id, room_number),
     room_type VARCHAR(20) NOT NULL,
     room_beds_number SMALLINT NOT NULL,
     room_number SMALLINT NOT NULL,
     room_price BIGINT NOT NULL,
     room_description VARCHAR(255),
     room_available BOOLEAN DEFAULT TRUE,
     hotel_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS additional_services (
    service_id BIGINT PRIMARY KEY,
    service_name VARCHAR(20) NOT NULL,
    service_type VARCHAR(20) NOT NULL,
    service_description VARCHAR(255),
    service_price BIGINT NOT NULL,
    service_enabled BOOLEAN DEFAULT TRUE,
    hotel_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS excursions (
    excursion_id BIGINT PRIMARY KEY,
    excursion_name VARCHAR(20) NOT NULL,
    excursion_description VARCHAR(255),
    excursion_price BIGINT NOT NULL,
    excursion_enabled BOOLEAN DEFAULT TRUE,
    hotel_id BIGINT NOT NULL
);

