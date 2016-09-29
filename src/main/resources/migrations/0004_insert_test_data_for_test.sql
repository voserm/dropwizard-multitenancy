--liquibase formatted sql

--changeset exampleservice:1
insert into tests (name, description)
VALUES
('test1', 'test1 desc'),
('test2', 'test2 desc');