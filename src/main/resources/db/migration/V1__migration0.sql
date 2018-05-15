-- Created by Gabor Tavali

-- tables

-- Table: generation
CREATE TABLE generation (
    generation_id SERIAL NOT NULL PRIMARY KEY,
    name CHAR(64) NOT NULL,
    row_size INT,
    column_size INT,
    UNIQUE (name)
);

-- Table: cell
CREATE TABLE cell (
    cell_id SERIAL NOT NULL PRIMARY KEY,
    row INT NOT NULL,
    column INT NOT NULL,
    generation_id INT
);

ALTER TABLE cell
   ADD CONSTRAINT fk_cell_generation
   FOREIGN KEY (generation_id)
   REFERENCES generation(generation_id);