--liquibase formatted sql

--changeset exampleservice:1

CREATE TABLE tests (
  id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  created_at datetime,
  updated_at datetime
);