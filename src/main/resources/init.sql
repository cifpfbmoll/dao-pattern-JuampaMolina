DROP TABLE IF EXISTS fruits;
CREATE TABLE fruits (
    id          SERIAL       NOT NULL UNIQUE,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO fruits (id, name, description) VALUES
(100, 'Orange', 'Summer fruit'),
(200, 'Strawberry', 'Winter fruit');
