CREATE TABLE IF NOT EXISTS hotels(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(40) UNIQUE NOT NULL,
    type VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS rooms(
    id UUID PRIMARY KEY,
    hotel_id UUID NOT NULL,
    number SMALLINT NOT NULL,
    type VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    available BOOLEAN DEFAULT TRUE,
    beds_number SMALLINT NOT NULL,
    price FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS additional_services (
    id UUID PRIMARY KEY,
    hotel_id UUID NOT NULL,
    name VARCHAR(40) NOT NULL,
    type VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    price FLOAT NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS excursions (
    id UUID PRIMARY KEY,
    hotel_id UUID NOT NULL,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    price FLOAT NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

